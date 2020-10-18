package com.example.onlyoffice.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.onlyoffice.R
import kotlinx.android.synthetic.main.fragment_loading_dialog.*
import kotlinx.android.synthetic.main.fragment_message_dialog.*

class MessageDialogFragment : AppCompatDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_dialog, container, false)
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
        arguments?.apply {

        }
        val onOK: (() -> Unit)? = arguments?.getSerializable(ON_OK_KEY) as () -> Unit


    }

    companion object {

        private const val HEADER_KEY = "header key"
        private const val MESSAGE_KEY = "message key"
        private const val ON_OK_KEY = "on cancel"

        @JvmStatic
        fun newInstance(header: String, message: String, onOK: (() -> Unit)? = null) =
            MessageDialogFragment().apply {
                arguments?.let { bundle ->
                    bundle.putString(HEADER_KEY, header)
                    bundle.putString(MESSAGE_KEY, message)
                    onOK?.let { _ ->
                        bundle.putSerializable(ON_OK_KEY, onOK as java.io.Serializable)
                    }
                }
            }
    }
}