package com.zaripov.waveaccesstestwork.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.presentation.view.ProfileView
import com.zaripov.waveaccesstestwork.presentation.presenter.ProfilePresenter

import com.arellomobile.mvp.MvpAppCompatActivity


class ProfileActivity : MvpAppCompatActivity(), ProfileView {
    companion object {
        const val TAG = "ProfileActivity"
        fun getIntent(context: Context): Intent = Intent(context, ProfileActivity::class.java)
    }

    @InjectPresenter
    lateinit var mProfilePresenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}
