package com.example.onlyoffice.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.onlyoffice.R
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
        initView()
    }

    private fun initView() {
        message_dialog_btn.setOnClickListener {
            dialog?.cancel()
        }
        arguments?.let { bundle ->
            message_dialog_header.text = bundle.getString(HEADER_KEY)
            message_dialog_text.text = bundle.getString(MESSAGE_KEY)
        }
    }

    companion object {

        const val TAG = "message dialog"

        private const val HEADER_KEY = "header key"
        private const val MESSAGE_KEY = "message key"

        @JvmStatic
        fun newInstance(header: String, message: String) =
            MessageDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(HEADER_KEY, header)
                    putString(MESSAGE_KEY, message)
                }
            }
    }
}