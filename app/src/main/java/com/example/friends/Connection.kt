package com.example.friends

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Connection (
    @PrimaryKey val connectionId: Long,
    val friendId: Long,
    val channelId: Long,
    val connectionDate: Date,
    val connectionLength: ConnectionLength
)