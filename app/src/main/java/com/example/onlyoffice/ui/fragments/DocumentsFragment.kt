package com.example.onlyoffice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlyoffice.R
import com.example.onlyoffice.common.list_adapters.Constants
import com.example.onlyoffice.common.list_adapters.ListAdapter
import com.example.onlyoffice.common.list_adapters.ViewType
import com.example.onlyoffice.common.list_adapters.adapters.FileAdapter
import com.example.onlyoffice.common.list_adapters.adapters.FolderAdapter
import com.example.onlyoffice.mvp.presenters.DocumentsFragmentPresenter
import com.example.onlyoffice.mvp.views.DocumentsFragmentView
import kotlinx.android.synthetic.main.fragment_documents.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DocumentsFragment : MvpAppCompatFragment(), DocumentsFragmentView {

    private val presenter by moxyPresenter {
        val folderId = arguments?.let { bundle ->
            bundle.getString(FOLDER_ID_TAG, ConstantDocumentsId.ERROR_ID.value)
        } ?: ConstantDocumentsId.ERROR_ID.value
        DocumentsFragmentPresenter(folderId)
    }
    private val adapter = ListAdapter(
        Constants.FILE to FileAdapter(),
        Constants.FOLDER to FolderAdapter{folderId ->
            presenter.moveToFolder(folderId)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun updateItemList(items: ArrayList<ViewType>) {
        adapter.items = items
    }

    override fun showErrorDialog(header: String, message: String) {
        TODO()
    }

    override fun toggleLoading(isShowing: Boolean) {
        fr_documents_loading.visibility = if (isShowing) VISIBLE else GONE
    }

    override fun togglePlaceholder(isShowing: Boolean) {
        fr_documents_message.visibility = if (isShowing) VISIBLE else GONE
    }

    override fun toggleItemList(isShowing: Boolean) {
        fr_documents_rw.visibility = if (isShowing) VISIBLE else GONE
    }

    private fun initViews() {
        fr_documents_rw.layoutManager = LinearLayoutManager(activity)
        fr_documents_rw.adapter = adapter
    }

    companion object {

        private const val FOLDER_ID_TAG = "folder id"

        @JvmStatic
        fun newInstance(folderId: String) = DocumentsFragment().apply {
            arguments = Bundle().apply {
                putString(FOLDER_ID_TAG, folderId)
            }
        }

        enum class ConstantDocumentsId(val value: String) {
            MY_DOCUMENTS_ID("@my"), COMMON_DOCUMENTS_ID("@common"), ERROR_ID("@error")
        }

    }
}