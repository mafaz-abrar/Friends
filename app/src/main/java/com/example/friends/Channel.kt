package com.example.friends

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey val channelId: Long,
    val channelName: String,
)