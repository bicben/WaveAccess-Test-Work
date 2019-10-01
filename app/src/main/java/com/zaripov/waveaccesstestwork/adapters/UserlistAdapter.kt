package com.zaripov.waveaccesstestwork.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zaripov.waveaccesstestwork.databinding.UserListItemBinding
import com.zaripov.waveaccesstestwork.model.User

class UserlistAdapter(private val clickListener: UserlistClickListener) :
    ListAdapter<User, UserlistAdapter.UserlistViewHolder>(UserDiffCallback())
     {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserlistViewHolder {
        return UserlistViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserlistViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class UserlistViewHolder private constructor(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User, clickListener: UserlistClickListener) {
            binding.user = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserlistViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserListItemBinding.inflate(layoutInflater, parent, false)

                return UserlistViewHolder(binding)
            }
        }
    }

    class UserDiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(p0: User, p1: User): Boolean {
            return p0 == p1
        }

        override fun areContentsTheSame(p0: User, p1: User): Boolean {
            return p0.id == p1.id
        }
    }
}