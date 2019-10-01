package com.zaripov.waveaccesstestwork.ui.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log

import com.arellomobile.mvp.presenter.InjectPresenter
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.presentation.view.ProfileView
import com.zaripov.waveaccesstestwork.presentation.presenter.ProfilePresenter

import com.arellomobile.mvp.MvpAppCompatActivity
import com.zaripov.waveaccesstestwork.adapters.*
import com.zaripov.waveaccesstestwork.databinding.ActivityProfileBinding
import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.User
import com.zaripov.waveaccesstestwork.ui.fragment.ContactsTabFragment
import com.zaripov.waveaccesstestwork.ui.fragment.FriendsTabFragment
import com.zaripov.waveaccesstestwork.ui.fragment.InfoTabFragment


class ProfileActivity : MvpAppCompatActivity(), ProfileView, TabFragmentInteractor {
    companion object {
        const val TAG = "ProfileActivity"
        const val ID_KEY_EXTRA = "id_key_extra"
        fun getIntent(context: Context): Intent = Intent(context, ProfileActivity::class.java)
    }

    @InjectPresenter
    lateinit var mProfilePresenter: ProfilePresenter
    lateinit var binding: ActivityProfileBinding
    private lateinit var alertDialog: AlertDialog

    private val infoFragment = InfoTabFragment.newInstance()
    private val contactsFragment = ContactsTabFragment.newInstance()
    private val friendsFragment = FriendsTabFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_profile)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        savedInstanceState ?: run {
            val idExtra = intent.getLongExtra(ID_KEY_EXTRA, 0L)
            mProfilePresenter.loadAndDisplayUser(idExtra)
        }
    }

    override fun setData() {
        Log.i(TAG, "setData")
        binding.user = mProfilePresenter.user
        inflatePageViewer()
    }

    override fun getUser() = mProfilePresenter.user

    override fun getFriends() = mProfilePresenter.friends

    override fun makeCall(number: String) {
    }

    override fun makeEmail(address: String) {
    }

    override fun openMap(lat: Double, lon: Double) {
    }

    private fun inflatePageViewer() {
        val pageAdapter = ProfilePagerAdapter(supportFragmentManager)
        pageAdapter.apply {
            addFragment(infoFragment, getString(R.string.info))
            addFragment(contactsFragment, getString(R.string.contacts))
            addFragment(friendsFragment, getString(R.string.friends))
        }
        binding.viewPager.adapter = pageAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun displayAlert(message: String) {
        alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.oops))
            .setMessage(message)
            .show()
    }
}
