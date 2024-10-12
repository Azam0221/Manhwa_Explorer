package com.example.manhwaexplorer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteManhwa::class], version = 2, exportSchema = false)
abstract class ManhwaDatabase : RoomDatabase() {


    abstract fun favoriteManhwaDao(): FavoriteManhwaDao


    companion object {
        @Volatile
        private var Instance: ManhwaDatabase? = null


        fun getDatabase(context: Context): ManhwaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ManhwaDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}