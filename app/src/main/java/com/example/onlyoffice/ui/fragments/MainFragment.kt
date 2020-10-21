package com.example.onlyoffice.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.onlyoffice.R
import com.example.onlyoffice.common.api.only_office.responses.UserInfoResponse
import com.example.onlyoffice.mvp.presenters.MainFragmentPresenter
import com.example.onlyoffice.mvp.views.MainFragmentView
import com.example.onlyoffice.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fr_main_nav_view_header.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainFragment : MvpAppCompatFragment(), MainFragmentView {

    private lateinit var toolbar: Toolbar

    private val presenter by moxyPresenter { MainFragmentPresenter() }

    private lateinit var navigator: SupportAppNavigator
    private lateinit var header: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProperties()
        initToolbar()
        initDrawer()
    }

    override fun onResume() {
        super.onResume()
        presenter.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        presenter.cicerone.navigatorHolder.removeNavigator()
    }

    override fun toggleHeaderLoading(isShowing: Boolean) {
        header.fr_main_drawer_layout_header_loading.visibility = if (isShowing) VISIBLE else GONE
        header.fr_main_drawer_layout_header_user_info.visibility = if (isShowing) GONE else VISIBLE
    }

    override fun updateUserInfoToHeader(userInfo: UserInfoResponse) {
        header.fr_main_drawer_layout_header_name.text = userInfo.response.userName
        header.fr_main_drawer_layout_header_email.text = userInfo.response.email
        Glide.with(this)
            .load(userInfo.response.avatar)
            .circleCrop()
            .into(header.fr_main_drawer_layout_header_avatar)
    }

    override fun toggleHeaderPlaceholder(isShowing: Boolean) {
        TODO("Not yet implemented")
    }

    private fun initToolbar() {
        toolbar = fr_main_toolbar
        activity?.let { activity ->
            if (activity is MainActivity) {
                activity.apply {
                    setSupportActionBar(toolbar)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                    supportActionBar!!.setHomeButtonEnabled(true)
                }
            }
        }
        fr_main_nav_view.setCheckedItem(R.id.fr_main_nav_view_my_documents)
        fr_main_nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fr_main_nav_view_my_documents -> {
                    presenter.onMyDocumentsClicked()
                    true
                }
                R.id.fr_main_nav_view_common_documents -> {
                    presenter.onCommonDocumentsClicked()
                    true
                }
                else -> false
            }
        }
    }

    private fun initDrawer() {
        val drawerToggle = ActionBarDrawerToggle(
            activity,
            fr_main_drawer_layout,
            toolbar,
            R.string.open_nav_drawer,
            R.string.close_nav_drawer
        )
        fr_main_drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun initProperties() {
        header = fr_main_nav_view.getHeaderView(0)
        navigator = SupportAppNavigator(activity, childFragmentManager, R.id.fr_main_container)
    }

    private fun initViews(){
        fr_main_nav_view_logout.setOnClickListener {
            presenter.onLogoutClicked()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}