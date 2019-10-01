package com.zaripov.waveaccesstestwork.ui.fragment


import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.adapters.TabFragmentInteractor
import com.zaripov.waveaccesstestwork.adapters.format
import com.zaripov.waveaccesstestwork.databinding.FragmentContactsTabBinding

class ContactsTabFragment : Fragment() {

    lateinit var binding: FragmentContactsTabBinding
    private lateinit var interactor: TabFragmentInteractor

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts_tab, container, false)

        val user = interactor.getUser()

        binding.user = user
        binding.tvContactsPhone.setOnClickListener { makeCall(user.phone) }
        binding.tvContactsEmail.setOnClickListener { makeEmail(user.email) }
        binding.tvContactsCoordinates.setOnClickListener { openMapCoordinates(user.latitude, user.longitude) }
        binding.tvContactsAddress.setOnClickListener { openMapAddress(user.address) }

        return binding.root
    }

    private fun makeEmail(address: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        intent.type = "message/rfc822"

        context?.startActivity(Intent.createChooser(intent, getString(R.string.select_email_app)))
    }

    private fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")

        context?.startActivity(intent)
    }

    private fun openMapCoordinates(lat: Double, lon: Double) {
        val uri = "geo:${lat.format(6)},${lon.format(6)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")

        context?.startActivity(intent)
    }

    private fun openMapAddress(address:String){
        val uri = "geo:0,0?q=$address?z=21"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")

        context?.startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactsTabFragment()
    }
}
