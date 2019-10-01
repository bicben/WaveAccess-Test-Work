package com.zaripov.waveaccesstestwork.ui.fragment


import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.adapters.TabFragmentInteractor
import com.zaripov.waveaccesstestwork.adapters.UserlistAdapter
import com.zaripov.waveaccesstestwork.adapters.UserlistClickListener
import com.zaripov.waveaccesstestwork.databinding.FragmentFriendsTabBinding
import com.zaripov.waveaccesstestwork.model.User
import com.zaripov.waveaccesstestwork.ui.activity.ProfileActivity

class FriendsTabFragment : Fragment(), UserlistClickListener{

    lateinit var binding: FragmentFriendsTabBinding
    private lateinit var interactor: TabFragmentInteractor
    private lateinit var adapter: UserlistAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TabFragmentInteractor) {
            interactor = context
        } else {
            throw RuntimeException("$context must implement TabFragmentInteractor")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_tab, container, false)

        adapter = UserlistAdapter(this)
        adapter.submitList(interactor.getFriends())
        binding.rvFriends.adapter = adapter

        return binding.root
    }

    override fun onClick(id: Long, active: Boolean) {
        if (active) {
            val intent = ProfileActivity.getIntent(context!!)
            intent.putExtra(ProfileActivity.ID_KEY_EXTRA, id)

            startActivity(intent)
        } else {
            Toast.makeText(context, getString(R.string.user_not_active), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FriendsTabFragment()
    }
}
