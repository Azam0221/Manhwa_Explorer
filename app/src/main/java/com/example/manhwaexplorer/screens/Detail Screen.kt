package com.example.manhwaexplorer.screens

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.manhwaexplorer.R
import com.example.manhwaexplorer.data.Manhwa

@OptIn(ExperimentalMaterial3Api::class)



@Composable
fun DetailScreen(manhwa: Manhwa,navController: NavController,viewModel: ManhwaViewModel = viewModel(factory = ManhwaViewModel.factory)
                ){
    Scaffold(
        topBar = {
            Box( modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),

                        ) {
                            IconButton(onClick = {navController.navigate("home")}) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "back"
                                )

                            }

                            Text("Manhwa Explorer", modifier = Modifier.padding(start = 80.dp))
                        }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFA8072))

                )
            }
        }
    ) { innerPadding->

        var isFavorite by remember { mutableStateOf(false) }

        Column (modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFA8072), // Start color
                        Color(0xFFB8D89D)
                    )
                )),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

      ){
            Spacer(modifier = Modifier.height(24.dp))

            Card(modifier = Modifier.padding(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                )
            {
    Column (modifier = Modifier.padding(10.dp)
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF0F0F0F), // Start color
                    Color(0xFFB8D89D)
                )
            )),
        verticalArrangement = Arrangement.Center,){
     Text(
        (stringResource(manhwa.title)),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Cyan,
        modifier = Modifier.padding(0.dp)

    )



            Image(
                painter = painterResource(manhwa.imageResourceId),
                contentDescription = "image",
                modifier = Modifier.size(500.dp)

            )
            Row(modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Text(text = "Add to Favorite", fontSize = 20.sp)

                FavoriteButton(manhwa = manhwa,
                    viewModel = viewModel,
                    onFavoriteClick = { newFavoriteState ->
                        isFavorite = newFavoriteState
                        if (newFavoriteState) {
                            viewModel.addFavorite(manhwa)
                        } else {
                            viewModel.removeFavorite(manhwa) // You should have a remove function in your ViewModel
                        }
                    }
                )
            }


}
            }
            Text((stringResource(manhwa.detailedDescription)),
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Start,
                fontSize = 20.sp
                )

            Spacer(modifier = Modifier.padding(top = 30.dp))

            RatingBar(rating = 3)
            Spacer(modifier = Modifier.padding(12.dp))
            Row {
                Text(
                    text = "Average Rating",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "4.3")

                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "ratings",
                    tint = Color(0xFFFA8072)
                )

            }
            Spacer(modifier = Modifier.padding(20.dp))



        }

}
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 50.dp else 42.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )

    Text(text = "Rate the Manhwa",
        fontSize = 20.sp )

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = if(i<=rating)Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "star",
                modifier = modifier
                    .width(size)
                    .height(size)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected = true
                                ratingState = i
                            }

                            MotionEvent.ACTION_UP -> {
                                selected = false
                            }
                        }
                        true
                    },
                tint = if (i <= ratingState) Color.Red else Color(0xFFA2ADB1)
            )
        }
    }
}

@Composable
fun FavoriteButton(
    manhwa: Manhwa,
    viewModel: ManhwaViewModel,
    onFavoriteClick: (Boolean) -> Unit
) {


    // Observe the favorite list to determine the button state
    val favoriteList by viewModel.favoriteManhwaList.observeAsState(emptyList())
    val isFavorite = favoriteList.any { it.title == manhwa.title }
    IconButton(
        onClick = {
            onFavoriteClick(!isFavorite)



        }
    ) {
        if (isFavorite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Unfavorite",
                tint = Color.Red // Change the color to red when favorited
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Gray // Change the color to gray when not favorited
            )
        }
    }
}
