package com.example.manhwaexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.manhwaexplorer.data.Manhwa
import com.example.manhwaexplorer.data.manhwa
import com.example.manhwaexplorer.screens.DetailScreen
import com.example.manhwaexplorer.screens.FavoritesScreen
import com.example.manhwaexplorer.screens.HomeScreen
import com.example.manhwaexplorer.screens.ManhwaViewModel
import com.example.manhwaexplorer.ui.theme.ManhwaExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ManhwaExplorerTheme {
                val navController = rememberNavController()




                NavHost(
                    navController = navController,
                    startDestination = "home",
                    builder = {
                        composable(route = "home"){
                            HomeScreen(navController)
                        }

                        composable("detail/{title}/{imageResourceId}/{detailedDescription}",
                            arguments = listOf(
                                navArgument("title") { type = NavType.IntType },
                                navArgument("imageResourceId") { type = NavType.IntType },
                                navArgument("detailedDescription") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val title = backStackEntry.arguments?.getInt("title") ?: R.string.title_one
                            val imageResourceId = backStackEntry.arguments?.getInt("imageResourceId") ?: R.drawable.manhwa_one
                            val detailedDescription = backStackEntry.arguments?.getInt("detailedDescription") ?: R.string.detail_one

                            val manhwa = Manhwa(title, imageResourceId, R.string.brief_one, R.string.creator_one,R.string.reads_one,detailedDescription)
                            DetailScreen(manhwa = manhwa, navController = navController)
                        }
                        composable("favorite"){
                            FavoritesScreen(navController)
                        }

                    }
                )
            }
        }
    }
}