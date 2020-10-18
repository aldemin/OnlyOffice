package com.example.onlyoffice.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.onlyoffice.R
import kotlinx.android.synthetic.main.fragment_loading_dialog.*

class LoadingDialogFragment : AppCompatDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        initView()
    }

    private fun initDialog() {
        dialog?.setCancelable(false)
    }

    private fun initView() {
        val onCancel: (() -> Unit)? = arguments?.getSerializable(ON_CANCEL_KEY) as () -> Unit
        if (onCancel != null) {
            dialog_loading_cancel_btn.setOnClickListener{_ ->
                onCancel.invoke()
                dialog?.cancel()
            }
        } else {
            dialog_loading_cancel_btn.visibility = View.GONE
        }

    }

    companion object {

        private const val ON_CANCEL_KEY = "on cancel"

        @JvmStatic
        fun newInstance(onCancel: (() -> Unit)? = null) = LoadingDialogFragment().apply {
            onCancel?.let {
                arguments?.putSerializable(ON_CANCEL_KEY, onCancel as java.io.Serializable)
            }
        }
    }
}