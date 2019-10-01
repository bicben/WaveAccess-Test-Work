package com.zaripov.waveaccesstestwork.adapters

import com.zaripov.waveaccesstestwork.model.User

interface TabFragmentInteractor {
    fun getUser(): User
    fun getFriends(): List<User>
}