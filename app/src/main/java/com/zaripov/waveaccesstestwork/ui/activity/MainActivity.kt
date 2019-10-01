package com.zaripov.waveaccesstestwork.ui.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.adapters.UserlistAdapter
import com.zaripov.waveaccesstestwork.adapters.UserlistClickListener
import com.zaripov.waveaccesstestwork.databinding.ActivityMainBinding
import com.zaripov.waveaccesstestwork.model.User
import com.zaripov.waveaccesstestwork.presentation.presenter.MainPresenter
import com.zaripov.waveaccesstestwork.presentation.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView, UserlistClickListener {

    companion object {
        const val TAG = "MainActivity"
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: UserlistAdapter
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listAdapter = UserlistAdapter(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.rvUserList.adapter = listAdapter

        setSupportActionBar(binding.listViewToolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        item?.let {
            when (it.itemId) {
                R.id.menu_item_nuke_db -> nukeDb()
            }
        }

        return true
    }

    override fun setData(users: List<User>) {
        listAdapter.submitList(users)
    }

    override fun setLoading(loading: Boolean) {
        if (loading) {
            binding.rvUserList.visibility = View.GONE
            binding.pbUserList.visibility = View.VISIBLE
        } else {
            binding.rvUserList.visibility = View.VISIBLE
            binding.pbUserList.visibility = View.GONE
        }
    }

    override fun displayAlert(message: String) {
        alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.oops))
            .setMessage(message)
            .show()
    }

    override fun onClick(id: Long, active: Boolean) {
        if (active) {
            val intent = ProfileActivity.getIntent(this)
            intent.putExtra(ProfileActivity.ID_KEY_EXTRA, id)

            startActivity(intent)
        } else {
            Toast.makeText(this, getString(R.string.user_not_active), Toast.LENGTH_SHORT).show()
        }
    }

    private fun nukeDb() {
        alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirmation))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ -> mMainPresenter.deleteUsers(); dialog.dismiss() }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
