package com.zaripov.waveaccesstestwork.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Relation
import android.support.annotation.NonNull


class Model{
    @Embedded
    lateinit var user: User
    @Relation(parentColumn = "id", entity = Friend::class, entityColumn = "userId")
    lateinit var friends: List<Friend>
    @Relation(parentColumn = "id", entity = Tag::class, entityColumn = "userId")
    lateinit var tags: List<Tag>

    override fun toString(): String {
        return "${user.name} ${friends.size} ${tags.size}\n"
    }
}

@Entity(primaryKeys = ["id"])
data class User(
    @NonNull
    val id: Long,
    val about: String,
    val address: String,
    val age: Int,
    val balance: String,
    val company: String,
    val email: String,
    val eyeColor: String,
    val favoriteFruit: String,
    val gender: String,
    @NonNull
    val guid: String,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: String,
    val registered: Long
)

@Entity(foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE)],
    primaryKeys = ["userId", "friendId"])
data class Friend(
    val userId: Long,
    val friendId: Long
)

@Entity(foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE)],
    primaryKeys = ["userId", "tag"])
data class Tag(
    val userId: Long,
    val tag: String
)