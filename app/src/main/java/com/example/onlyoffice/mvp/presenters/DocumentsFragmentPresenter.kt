package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.api.only_office.FilesAPI
import com.example.onlyoffice.common.list_adapters.FileViewType
import com.example.onlyoffice.common.list_adapters.FolderViewType
import com.example.onlyoffice.common.list_adapters.ViewType
import com.example.onlyoffice.common.navigation.cicerone.DocumentsFragmentAppScreen
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.common.providers.DocumentsFragmentStringProvider
import com.example.onlyoffice.common.providers.UserInfoProvider
import com.example.onlyoffice.model.responses.FilesResponse
import com.example.onlyoffice.model.responses.FilesResponse.Response.File
import com.example.onlyoffice.model.responses.FilesResponse.Response.Folder
import com.example.onlyoffice.mvp.views.DocumentsFragmentView
import com.example.onlyoffice.ui.fragments.DocumentsFragment.Companion.ConstantDocumentsId.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class DocumentsFragmentPresenter(
    private val folderId: String
) : MvpPresenter<DocumentsFragmentView>() {

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
    lateinit var stringProvider: DocumentsFragmentStringProvider

    @Inject
    lateinit var userInfoProvider: UserInfoProvider

    private lateinit var filesAPI: FilesAPI

    private var filesDisposable: Disposable? = null
    private val filesObserver = object : Observer<FilesResponse> {

        override fun onNext(response: FilesResponse) {
            viewState.toggleLoading(false)
            viewState.toggleItemList(true)
            viewState.togglePlaceholder(false)
            updateItemList(response.response.folders, response.response.files)
        }

        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
            filesDisposable = d
            viewState.toggleLoading(true)
            viewState.toggleItemList(false)
            viewState.togglePlaceholder(false)
        }

        override fun onError(e: Throwable) {
            viewState.showErrorDialog(
                stringProvider.headerLoadingFilesErrorMessage,
                e.localizedMessage
            )
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        if (authInfoProvider.isAuthorization()) {
            initProperties()
            loadFiles(folderId)
        } else {
            appCicerone.router.newRootScreen(LoginFragmentAppScreen())
        }
    }

    private fun initProperties() {
        filesAPI = apiBuilder
            .baseUrl(authInfoProvider.portal)
            .build()
            .create(FilesAPI::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        filesDisposable?.dispose()
    }

    private fun loadFiles(folderId: String) {
        when (folderId) {
            MY_DOCUMENTS_ID.value -> {
                filesAPI.getMyFiles(authInfoProvider.token!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(filesObserver)
            }
            COMMON_DOCUMENTS_ID.value -> {
                filesAPI.getCommonFiles(authInfoProvider.token!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(filesObserver)
            }
            ERROR_ID.value -> {
                TODO()
            }
            else -> {
                filesAPI.getFileById(
                    authInfoProvider.token!!,
                    folderId,
                    userInfoProvider.userInfoResponse!!.response.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(filesObserver)
            }
        }
    }

    private fun updateItemList(foldersList: List<Folder>?, filesList: List<File>?) {
        val itemList = arrayListOf<ViewType>()
        if (!foldersList.isNullOrEmpty()) {
            for (folder in foldersList) {
                itemList.add(FolderViewType(folder))
            }
        }
        if (!filesList.isNullOrEmpty()) {
            for (file in filesList) {
                itemList.add(FileViewType(file))
            }
        }
        if (itemList.isEmpty()) {
            viewState.toggleLoading(false)
            viewState.toggleItemList(false)
            viewState.togglePlaceholder(true)
        } else {
            viewState.updateItemList(itemList)
        }
    }

    fun moveToFolder(folderId: String) {
        mainCicerone.router.navigateTo(DocumentsFragmentAppScreen(folderId))
    }

}