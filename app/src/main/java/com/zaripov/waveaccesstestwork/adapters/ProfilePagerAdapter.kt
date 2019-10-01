package com.zaripov.waveaccesstestwork.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ProfilePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()
    private val labels = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return labels[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment, label: String){
        fragments.add(fragment)
        labels.add(label)
    }
}