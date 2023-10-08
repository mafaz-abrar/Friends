package com.example.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FriendsViewModel(private val database: FriendsDatabase) : ViewModel() {
    // Pass in the singleton DB.
    val allFriends: LiveData<List<Friend>> = database.getFriendsDao().getAllFriends().asLiveData()

    fun insert(friend: Friend) = viewModelScope.launch {
        database.getFriendsDao().insert(friend)
    }

    fun deleteAll() = viewModelScope.launch {
        database.getFriendsDao().deleteAll()
    }
}

class FriendsViewModelFactory(private val database: FriendsDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FriendsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}