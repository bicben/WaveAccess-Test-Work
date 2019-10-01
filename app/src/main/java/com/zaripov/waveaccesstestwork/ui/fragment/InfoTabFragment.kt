package com.zaripov.waveaccesstestwork.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.adapters.TabFragmentInteractor
import com.zaripov.waveaccesstestwork.databinding.FragmentInfoTabBinding


class InfoTabFragment : Fragment() {

    lateinit var binding: FragmentInfoTabBinding
    private lateinit var interactor: TabFragmentInteractor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_tab, container, false)
        binding.user = interactor.getUser()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TabFragmentInteractor) {
            interactor = context
        } else {
            throw RuntimeException("$context must implement TabFragmentInteractor")
        }
    }

    companion object {
        const val TAG = "InfoTabFragment"

        @JvmStatic
        fun newInstance() = InfoTabFragment()
    }
}
