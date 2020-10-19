package com.example.onlyoffice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.onlyoffice.R
import com.example.onlyoffice.mvp.presenters.LoginFragmentPresenter
import com.example.onlyoffice.mvp.views.LoginFragmentView
import com.example.onlyoffice.ui.dialogs.LoadingDialogFragment
import com.example.onlyoffice.ui.dialogs.MessageDialogFragment
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment : MvpAppCompatFragment(), LoginFragmentView {

    private val presenter by moxyPresenter { LoginFragmentPresenter() }

    private lateinit var loadingDialog: LoadingDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        fr_login_login_btn.setOnClickListener {
            presenter.checkPortal(fr_login_portal_ed.text.toString())
            presenter.checkLogin(fr_login_login_ed.text.toString())
            presenter.checkPassword(fr_login_password_ed.text.toString())
            presenter.okBtnClicked(
                fr_login_portal_ed.text.toString(),
                fr_login_login_ed.text.toString(),
                fr_login_password_ed.text.toString()
            )
        }
        fr_login_portal_ed.addTextChangedListener {
            presenter.checkPortal(it.toString())
        }

        fr_login_login_ed.addTextChangedListener {
            presenter.checkLogin(it.toString())
        }

        fr_login_password_ed.addTextChangedListener {
            presenter.checkPassword(it.toString())
        }
    }

    override fun toggleLoadingDialog(isShowing: Boolean, onCancel: (() -> Unit)?) {
        if (isShowing) {
            loadingDialog = LoadingDialogFragment.newInstance(onCancel)
            activity?.supportFragmentManager?.let {
                loadingDialog.isCancelable = false
                loadingDialog.show(it, LoadingDialogFragment.TAG)
            }
        } else {
            loadingDialog.dialog?.cancel()
        }
    }

    override fun showErrorDialog(header: String, message: String) {
        activity?.supportFragmentManager?.let {fragmentManager ->
            val dialog = MessageDialogFragment.newInstance(header, message)
            dialog.isCancelable = false
            dialog.show(fragmentManager, MessageDialogFragment.TAG)
        }
    }

    override fun setPortalError(message: String) {
        fr_login_portal_layout.error = message
    }

    override fun setLoginError(message: String) {
        fr_login_login_layout.error = message
    }

    override fun setPasswordError(message: String) {
        fr_login_password_layout.error = message
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}