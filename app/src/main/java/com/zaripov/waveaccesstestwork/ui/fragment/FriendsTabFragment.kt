package com.zaripov.waveaccesstestwork.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.model.User

class FriendsTabFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends_tab, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FriendsTabFragment()
    }
}
