package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.api.only_office.PeopleAPI
import com.example.onlyoffice.common.api.only_office.responses.UserInfoResponse
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.mvp.views.MainFragmentView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {

    @Inject
    @field:Named("MainRouter")
    lateinit var cicerone: Cicerone<Router>

    @Inject
    @field:Named("AppRouter")
    lateinit var appCicerone: Cicerone<Router>

    @Inject
    lateinit var apiBuilder: Retrofit.Builder

    @Inject
    lateinit var authInfoProvider: AuthInfoProvider

    private var userInfoDisposable: Disposable? = null
    private val userInfoObserver = object : SingleObserver<UserInfoResponse> {

        override fun onSubscribe(d: Disposable) {
            viewState.toggleHeaderLoading(true)
            userInfoDisposable = d
        }

        override fun onSuccess(response: UserInfoResponse) {
            viewState.toggleHeaderLoading(false)
            viewState.updateUserInfoToHeader(response)
        }

        override fun onError(e: Throwable) {
            viewState.toggleHeaderLoading(false)
            viewState.toggleHeaderPlaceholder(true)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        onMyDocumentsClicked()
        loadUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        userInfoDisposable?.dispose()
    }

    private fun loadUserInfo() {
        val peopleAPI = apiBuilder
            .baseUrl(authInfoProvider.portal)
            .build()
            .create(PeopleAPI::class.java)
        peopleAPI.getUserInfo(authInfoProvider.token!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(userInfoObserver)
    }

    fun onLogoutClicked() {
        appCicerone.router.newRootScreen(LoginFragmentAppScreen())
        authInfoProvider.logout()
    }

    fun onMyDocumentsClicked() {

    }

    fun onCommonDocumentsClicked() {

    }
}