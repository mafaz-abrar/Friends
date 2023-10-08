package com.example.friends

import android.app.Application

class FriendsApplication: Application() {
    val database by lazy { FriendsDatabase.getDatabase(this)}
}