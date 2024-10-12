package com.example.manhwaexplorer.screens

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.manhwaexplorer.ManhwaApplication
import com.example.manhwaexplorer.data.FavoriteManhwa
import com.example.manhwaexplorer.data.FavoriteManhwaDao
import com.example.manhwaexplorer.data.Manhwa
import kotlinx.coroutines.launch

class ManhwaViewModel(private val favoriteManhwaDao: FavoriteManhwaDao): ViewModel() {

    val listState = LazyListState()


    fun clearFavorites() {
        viewModelScope.launch {
            favoriteManhwaDao.clearFavorites()
        }
    }


    // LiveData for observing favorite manhwa list
    private val _favoriteManhwaList = MutableLiveData<List<FavoriteManhwa>>()
    val favoriteManhwaList: LiveData<List<FavoriteManhwa>> get() = _favoriteManhwaList

    init {
        // Load the favorites from the database when ViewModel is initialized
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            // Load favorites from the database
            _favoriteManhwaList.value = favoriteManhwaDao.getAllFavorites()
        }
    }


    fun addFavorite(manhwa: Manhwa){
        viewModelScope.launch {
            // Check if the manhwa is already in the favorites
            val existingFavorite = favoriteManhwaDao.getFavoriteByTitle(manhwa.title.toString())
            if (existingFavorite == null) {

                favoriteManhwaDao.addFavorite(
                    FavoriteManhwa(
                        title = manhwa.title,
                        imageResourceId = manhwa.imageResourceId,
                        creator = manhwa.creator
                    )
                )
                loadFavorites()
            }

        }
    }

    fun removeFavorite(manhwa: Manhwa){
        viewModelScope.launch {
            val existingFavorite = favoriteManhwaDao.getFavoriteByTitle(manhwa.title.toString())

                if (existingFavorite != null) {
                    favoriteManhwaDao.removeFavorite(existingFavorite)

                    loadFavorites()
                }
            }
        }



  companion object {
    val factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as ManhwaApplication)
            ManhwaViewModel(application.database.favoriteManhwaDao())
        }
    }
}
}
