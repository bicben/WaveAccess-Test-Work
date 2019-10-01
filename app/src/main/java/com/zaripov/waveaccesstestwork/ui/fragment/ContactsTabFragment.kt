package com.zaripov.waveaccesstestwork.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.model.User

class ContactsTabFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_tab, container, false)
    }

     fun makeEmail(address: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        intent.type = "message/rfc822"

        context?.startActivity(Intent.createChooser(intent, getString(R.string.select_email_app)))
    }

     fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        context?.startActivity(intent)
    }

     fun openMap(lat: Double, lon: Double) {
        val uri = "geo:$lat,$lon"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

        context?.startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactsTabFragment()
    }
}
