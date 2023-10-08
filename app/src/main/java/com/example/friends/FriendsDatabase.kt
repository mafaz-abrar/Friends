package com.example.friends

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Friend::class])
public abstract class FriendsDatabase: RoomDatabase() {
    abstract fun getFriendsDao(): FriendsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FriendsDatabase? = null

        fun getDatabase(context: Context): FriendsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendsDatabase::class.java,
                    "friends_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}