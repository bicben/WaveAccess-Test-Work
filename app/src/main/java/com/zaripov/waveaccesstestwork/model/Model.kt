package com.zaripov.waveaccesstestwork.model

import com.google.gson.annotations.SerializedName


data class Model(
    val user: User,
    val friends: List<Friend>,
    val tags: List<Tag>
)

data class User(
    @SerializedName("about")
    val about: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("favoriteFruit")
    val favoriteFruit: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("guid")
    val guid: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("registered")
    val registered: Long
)

data class Friend(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("friendId")
    val friendId: Int
)

data class Tag(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("tag")
    val tag: String
)