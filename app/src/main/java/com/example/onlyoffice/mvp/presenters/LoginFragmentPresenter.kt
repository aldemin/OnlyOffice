package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.api.only_office.AuthAPI
import com.example.onlyoffice.common.api.only_office.request.AuthorisationRequest
import com.example.onlyoffice.common.api.only_office.responses.AuthorisationResponse
import com.example.onlyoffice.mvp.views.LoginFragmentView
import com.example.onlyoffice.utils.LoginFragmentStringProvider
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import retrofit2.Retrofit
import javax.inject.Inject

class LoginFragmentPresenter : MvpPresenter<LoginFragmentView>() {

    @Inject
    lateinit var stringProvider: LoginFragmentStringProvider
    @Inject
    lateinit var auth: Retrofit.Builder

    private var isPortalErrorShowing = false
    private var isLoginErrorShowing = false
    private var isPasswordErrorShowing = false

    private val emptyString = ""

    private val portalRegex = Regex("^[a-z0-9_-]{6,50}\$")
    private val loginRegex = Regex("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")
    private val passwordRegex = Regex("^[a-zA-Z0-9_-]{8,}\$")

    private var authDisposable: Disposable? = null
    private val authObserver = object : SingleObserver<AuthorisationResponse> {

        override fun onSubscribe(d: Disposable) {
            authDisposable = d
            viewState.toggleLoadingDialog(true)
        }

        override fun onSuccess(t: AuthorisationResponse) {
            viewState.toggleLoadingDialog(false)
            viewState.moveToMainScreen()
        }

        override fun onError(e: Throwable) {
            viewState.toggleLoadingDialog(false)
            viewState.showErrorDialog(e.localizedMessage)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        authDisposable?.dispose()
    }

    fun okBtnClicked(portal: String, login: String, password: String) {
        if (!isLoginErrorShowing && !isPortalErrorShowing && !isPasswordErrorShowing) {
            val retrofit = auth
                .baseUrl("https://$portal.onlyoffice.eu/")
                .build()
                .create(AuthAPI::class.java)
            retrofit.authorization(AuthorisationRequest(login, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authObserver)
        }
    }

    fun checkPortal(portal: String) {
        isPortalErrorShowing = if (!portalRegex.matches(portal)) {
            viewState.setPortalError(stringProvider.portalErrorMessage)
            true
        } else {
            viewState.setPortalError(emptyString)
            false
        }
    }

    fun checkLogin(login: String) {
        isLoginErrorShowing = if (!loginRegex.matches(login)) {
            viewState.setLoginError(stringProvider.loginErrorMessage)
            true
        } else {
            viewState.setLoginError(emptyString)
            false
        }
    }

    fun checkPassword(password: String) {
        isPasswordErrorShowing = if (!passwordRegex.matches(password)) {
            viewState.setPasswordError(stringProvider.passwordErrorMessage)
            true
        } else {
            viewState.setPasswordError(emptyString)
            false
        }
    }
}