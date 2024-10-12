package com.example.manhwaexplorer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteManhwaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteManhwa: FavoriteManhwa)

    @Delete
    suspend fun removeFavorite(favoriteManhwa: FavoriteManhwa)

    @Query("SELECT * FROM favorite WHERE title = :title ")
    suspend fun getFavoriteByTitle(title: String): FavoriteManhwa?

    @Query("SELECT * FROM favorite ")
    suspend fun getAllFavorites(): List<FavoriteManhwa>

    @Query("DELETE FROM favorite") // Replace with your table name
    suspend fun clearFavorites()

}