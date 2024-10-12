package com.example.manhwaexplorer.data

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.manhwaexplorer.R

@Entity(tableName = "favorite")
data class FavoriteManhwa(
     @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @StringRes val title: Int,
    @DrawableRes val  imageResourceId: Int,
   @StringRes val  creator: Int


)


data class Manhwa(
    @StringRes val title :Int,
    @DrawableRes val imageResourceId : Int,
    @StringRes val creator : Int,
    @StringRes val reads : Int,
    @StringRes val briefDescription : Int,
    @StringRes val detailedDescription : Int
)

//
//data class ManhwaDetailed(
//    val detailedDescription : String
//
//)

val manhwa = listOf(
    Manhwa(R.string.title_one,R.drawable.manhwa_one,R.string.creator_one,R.string.reads_one,R.string.brief_one,R.string.detail_one),
    Manhwa(R.string.title_two,R.drawable.manhwa_two,R.string.creator_two,R.string.reads_two,R.string.brief_two,R.string.detail_two ),
    Manhwa(R.string.title_three,R.drawable.manhwa_three,R.string.creator_three,R.string.reads_three,R.string.brief_three,R.string.detail_three ),
    Manhwa(R.string.title_four,R.drawable.manhwa_four,R.string.creator_four,R.string.reads_four,R.string.brief_four,R.string.detail_four ),
    Manhwa(R.string.title_five,R.drawable.manhwa_five,R.string.creator_five,R.string.reads_five,R.string.brief_five,R.string.detail_five),
    Manhwa(R.string.title_six,R.drawable.manhwa_six,R.string.creator_six,R.string.reads_six,R.string.brief_six,R.string.detail_six),
    Manhwa(R.string.title_seven,R.drawable.manhwa_seven,R.string.creator_seven,R.string.reads_seven,R.string.brief_seven,R.string.detail_seven),
    Manhwa(R.string.title_eight,R.drawable.manhwa_eight,R.string.creator_eight,R.string.reads_eight,R.string.brief_eight,R.string.detail_eight),
    Manhwa(R.string.title_nine,R.drawable.manhwa_nine,R.string.creator_nine,R.string.reads_nine,R.string.brief_nine,R.string.detail_nine),
    Manhwa(R.string.title_ten,R.drawable.manhwa_ten,R.string.creator_ten,R.string.reads_ten,R.string.brief_ten,R.string.detail_ten)
)

