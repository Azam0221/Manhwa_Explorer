package com.example.manhwaexplorer.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.manhwaexplorer.R
import com.example.manhwaexplorer.data.Manhwa
import com.example.manhwaexplorer.data.manhwa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ManhwaViewModel = viewModel(factory = ManhwaViewModel.factory)){
    Scaffold(
        topBar = {
            Box( modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))) {
                TopAppBar(
                    title = { Text("Manhwa Explorer", modifier = Modifier.padding(start = 80.dp)) },
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


    ) { innerPadding->
        //val listState = viewModel.listState


        // Clear the existing entries

        Column(modifier = Modifier.padding(innerPadding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFA8072), // Start color
                        Color(0xFFB8D89D)
                    )
                )),
            ) {

            LazyColumn(
                modifier = Modifier){
                items(manhwa){
                    ManhwaItem(
                        manhwa = it,
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }


    }}
}
@Composable
fun ManhwaItem(
    manhwa: Manhwa,
    navController: NavController,
    modifier: Modifier = Modifier
){
    Card (modifier = Modifier.padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
        Column(modifier = Modifier.padding(10.dp)
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F0F0F), // Start color
                        Color(0xFFB8D89D)
                    )
                )),
            verticalArrangement = Arrangement.Center,
        ){
            TextButton(onClick = {
                navController.navigate(
                    "detail/${manhwa.title}/${manhwa.imageResourceId}/${manhwa.detailedDescription}"
                )
            }) {
            Text(stringResource(manhwa.title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan
            )}
            Spacer(modifier = Modifier.padding(8.dp))
         Image(
             painter = painterResource(manhwa.imageResourceId),
             contentDescription = "image",

         )
            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text(text = "⚫ Creator")
                Spacer(modifier = Modifier.padding(4.dp))

                Text(stringResource(manhwa.creator))
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row {
                Text(text = "⚫ Reads")
                Spacer(modifier = Modifier.padding(4.dp))

                Text(stringResource(manhwa.reads))
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Text(stringResource(manhwa.briefDescription))

        }

    }
}