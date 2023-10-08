package com.example.friends
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    @PrimaryKey(autoGenerate = true) val friendId: Long,
    val shortName: String,
    val fullName: String?,
)