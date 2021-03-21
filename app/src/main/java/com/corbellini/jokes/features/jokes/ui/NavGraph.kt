package com.corbellini.jokes.features.jokes.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.corbellini.jokes.features.jokes.ui.jokes.list.JokeScreen


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


