package com.example.onlyoffice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.onlyoffice.R
import com.example.onlyoffice.mvp.presenters.LoginFragmentPresenter
import com.example.onlyoffice.mvp.views.LoginFragmentView
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment : MvpAppCompatFragment(), LoginFragmentView {

    private val presenter by moxyPresenter { LoginFragmentPresenter() }

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

    override fun toggleLoadingDialog(isShowing: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showErrorDialog(message: String) {
        TODO("Not yet implemented")
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

    override fun moveToMainScreen() {
        TODO("Not yet implemented")
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}