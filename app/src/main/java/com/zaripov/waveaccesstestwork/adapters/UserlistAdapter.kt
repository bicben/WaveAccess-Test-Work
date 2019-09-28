package com.zaripov.waveaccesstestwork.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import com.zaripov.waveaccesstestwork.databinding.UserListItemBinding
import com.zaripov.waveaccesstestwork.model.Model

class UserlistAdapter(private val clickListener: UserlistClickListener) :
    RecyclerView.Adapter<UserlistAdapter.UserlistViewHolder>() {

    var models: List<Model> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserlistViewHolder {
        return UserlistViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: UserlistViewHolder, position: Int) {
        val item = models.get(position)
        holder.bind(item, clickListener)
    }

    class UserlistViewHolder private constructor(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Model, clickListener: UserlistClickListener) {
            binding.user = item.user
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
}