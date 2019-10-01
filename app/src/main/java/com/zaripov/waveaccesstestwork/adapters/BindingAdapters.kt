package com.zaripov.waveaccesstestwork.adapters

import android.content.res.ColorStateList
import android.databinding.BindingAdapter
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import com.zaripov.waveaccesstestwork.model.EyeColor
import com.zaripov.waveaccesstestwork.model.User
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter(value = ["bind_age"])
fun TextView.bindAge(user: User?) {
    user?.let {
        text = user.age.toString()
    }
}

@BindingAdapter(value = ["bind_registered"])
fun TextView.bindRegistered(user: User?) {
    user?.let {
        val format = SimpleDateFormat("HH:mm dd.MM.yy", Locale.ENGLISH)
        text = format.format(user.registered)
    }
}

@BindingAdapter(value = ["bind_eye_color"])
fun View.bindEyeColor(user: User?) {
    user?.let {
        ViewCompat.setBackgroundTintList(
            this,
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    WaveAccessApp.instance,
                    it.eyeColor.color
                )
            )
        )
    }
}

fun Double.format(digits: Int): String = String.format("%.${digits}f", this)

@BindingAdapter(value = ["bind_coordinates"])
fun TextView.bindCoordinates(user: User?) {
    user?.let {
        val coordinates = "${user.latitude.format(6)},${user.longitude.format(6)}"
        text = coordinates
    }
}
