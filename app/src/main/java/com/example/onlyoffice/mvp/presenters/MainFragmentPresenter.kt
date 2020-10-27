package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.api.only_office.PeopleAPI
import com.example.onlyoffice.common.navigation.cicerone.DocumentsFragmentAppScreen
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.common.providers.MainFragmentStringProvider
import com.example.onlyoffice.common.providers.UserInfoProvider
import com.example.onlyoffice.model.responses.UserInfoResponse
import com.example.onlyoffice.mvp.views.MainFragmentView
import com.example.onlyoffice.ui.fragments.DocumentsFragment.Companion.ConstantDocumentsId.COMMON_DOCUMENTS_ID
import com.example.onlyoffice.ui.fragments.DocumentsFragment.Companion.ConstantDocumentsId.MY_DOCUMENTS_ID
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
    lateinit var mainCicerone: Cicerone<Router>

    @Inject
    @field:Named("AppRouter")
    lateinit var appCicerone: Cicerone<Router>

    @Inject
    lateinit var apiBuilder: Retrofit.Builder

    @Inject
    lateinit var authInfoProvider: AuthInfoProvider

    @Inject
    lateinit var stringProvider: MainFragmentStringProvider

    @Inject
    lateinit var userInfoProvider: UserInfoProvider

    private lateinit var peopleAPI: PeopleAPI

    private var userInfoDisposable: Disposable? = null
    private val userInfoObserver = object : SingleObserver<UserInfoResponse> {

        override fun onSubscribe(d: Disposable) {
            viewState.toggleHeaderLoading(true)
            userInfoDisposable = d
        }

        override fun onSuccess(response: UserInfoResponse) {
            userInfoProvider.userInfoResponse = response
            viewState.toggleHeaderLoading(false)
            viewState.updateUserInfoToHeader(response)
        }

        override fun onError(e: Throwable) {
            viewState.toggleHeaderLoading(false)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        if (authInfoProvider.isAuthorization()) {
            initProperties()
            onMyDocumentsClicked()
            loadUserInfo()
        } else {
            appCicerone.router.replaceScreen(LoginFragmentAppScreen())
        }

    }

    private fun initProperties() {
        peopleAPI = apiBuilder
            .baseUrl(authInfoProvider.portal)
            .build()
            .create(PeopleAPI::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        userInfoDisposable?.dispose()
    }

    private fun loadUserInfo() {
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
        viewState.setToolbarTitle(stringProvider.myDocumentsToolbarTitle)
        mainCicerone.router.newRootScreen(DocumentsFragmentAppScreen(MY_DOCUMENTS_ID.value))
    }

    fun onCommonDocumentsClicked() {
        viewState.setToolbarTitle(stringProvider.commonDocumentsToolbarTitle)
        mainCicerone.router.newRootScreen(DocumentsFragmentAppScreen(COMMON_DOCUMENTS_ID.value))
    }

    fun onBackPressed(): Boolean {
        mainCicerone.router.exit()
        return true
    }
}