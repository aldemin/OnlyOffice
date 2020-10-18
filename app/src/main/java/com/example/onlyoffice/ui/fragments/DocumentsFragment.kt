package com.example.onlyoffice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlyoffice.R
import moxy.MvpAppCompatFragment

class DocumentsFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = DocumentsFragment()

    }
}