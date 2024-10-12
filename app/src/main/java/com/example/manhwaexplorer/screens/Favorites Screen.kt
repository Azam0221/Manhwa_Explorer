package com.example.manhwaexplorer.screens

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.manhwaexplorer.R
import com.example.manhwaexplorer.data.FavoriteManhwa
import com.example.manhwaexplorer.data.Manhwa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, viewModel: ManhwaViewModel = viewModel(factory = ManhwaViewModel.factory)

                    ) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            ) {
                TopAppBar(
                    title = { Text("Favorite Manhwa", modifier = Modifier.padding(start = 80.dp)) },
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFA8072))
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Row(modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.Center){

                        Spacer(modifier = Modifier.padding(start = 60.dp))
                        IconButton(onClick = {navController.navigate("home")}) {
                            Icon(painter = painterResource(R.drawable.home),
                                contentDescription = "Home")
                        }
                        Spacer(modifier = Modifier.padding(start = 160.dp))
                        IconButton(onClick = {navController.navigate("favorite")}) {
                            Icon(painter = painterResource(R.drawable.favorite),
                                contentDescription = "Favorite")
                        }

                    }
                }
            )
        }



    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFA8072), // Start color
                        Color(0xFFB8D89D)
                    )
                )),
        ) {

            val viewModel :ManhwaViewModel = viewModel()
            val favoriteManhwaList by viewModel.favoriteManhwaList.observeAsState(emptyList())

            LazyColumn {
                items(favoriteManhwaList) { manhwa ->
                    FavoriteManhwaCard(manhwa) // Call your card composable for each item
                }
            }





        }
    }
}
@Composable
fun FavoriteManhwaCard(manhwa: FavoriteManhwa) {
    Card(
        modifier = Modifier
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {
        Column(modifier = Modifier.padding(20.dp)
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F0F0F), // Start color
                        Color(0xFFB8D89D)
                    )
                )),
            verticalArrangement = Arrangement.Center){


            Spacer(modifier = Modifier.height(12.dp))
            Text(stringResource(manhwa.title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan
                )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = manhwa.imageResourceId),
                contentDescription = "image"// Set the size of the image
            )
            Spacer(modifier = Modifier.height(8.dp))

                Text(stringResource(manhwa.creator))


        }
    }
}