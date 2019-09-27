package com.zaripov.waveaccesstestwork.ui.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.databinding.ActivityMainBinding
import com.zaripov.waveaccesstestwork.presentation.presenter.MainPresenter
import com.zaripov.waveaccesstestwork.presentation.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        const val TAG = "MainActivity"
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @InjectPresenter
    lateinit var mMainViPresenter: MainPresenter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    override fun displayText(text: String) {
        binding.testText = text
    }
}
