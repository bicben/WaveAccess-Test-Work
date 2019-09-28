package com.zaripov.waveaccesstestwork.adapters

import android.databinding.BindingAdapter
import android.support.constraint.ConstraintLayout
import android.widget.ImageView
import android.widget.TextView
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.model.User

@BindingAdapter(value = ["bind_person_icon"])
fun ImageView.bindPersonIcon(user: User?) {
    user?.let {
        setImageResource(
            if (it.isActive) {
                R.drawable.ic_person_48dp
            } else {
                R.drawable.ic_person_outline_48dp
            }
        )

    }
}

@BindingAdapter(value = ["bind_user_active"])
fun TextView.bindActive(user: User?) {
    user?.let {
        text = if (it.isActive) {
            context.getString(R.string.active)
        } else {
            context.getString(R.string.inactive)
        }
    }
}

@BindingAdapter(value = ["bind_user_list_bg"])
fun ConstraintLayout.bindListItemBG(user: User?) {
    user?.let {
        setBackgroundResource(
            if (user.isActive) {
                R.color.colorPrimary
            } else {
                android.R.color.darker_gray
            }
        )
    }
}