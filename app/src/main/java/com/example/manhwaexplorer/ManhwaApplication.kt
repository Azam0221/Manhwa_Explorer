package com.example.manhwaexplorer

import android.app.Application
import com.example.manhwaexplorer.data.ManhwaDatabase


class ManhwaApplication  : Application(){

    val database : ManhwaDatabase by lazy { ManhwaDatabase.getDatabase(this) }
}