package com.corbellini.jokes.features.jokes.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.corbellini.jokes.features.jokes.ui.jokes.JokeScreen


sealed class NavScreens(val route: String) {
    object MAIN : NavScreens("main")
}

@Preview
@Composable
fun NavGraph(startDestination: NavScreens = NavScreens.MAIN) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(
            route = NavScreens.MAIN.route
        ) { NavScreen() }

    }
}


@Composable
fun NavScreen(
) {
        JokeScreen(
            hiltNavGraphViewModel(),
        )
}


