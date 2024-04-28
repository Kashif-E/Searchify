package com.kashif.searchify.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kashif.feature_search.presentation.DetailsScreen
import com.kashif.feature_search.presentation.MoviesViewModel
import com.kashif.feature_search.presentation.SearchScreen
import java.net.URLDecoder
import java.net.URLEncoder

enum class Route(val route: String) {
    List("list"), Details("details/{url}")
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.List.route,
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideInHorizontally { it } + fadeIn() },
            exitTransition = { slideOutHorizontally { -it } + fadeOut() },
            popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
            popExitTransition = { slideOutHorizontally { it } + fadeOut() },
        ) {

            composable(
                route = Route.List.route
            ) {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry(Route.List.route)
                }
                val viewModel: MoviesViewModel = hiltViewModel(parentEntry)
                SearchScreen(
                    viewModel = viewModel,
                    animatedVisibilityScope = this@composable,
                    onItemClick = { item ->
                        navController.navigate("details/${item.utf8()}")
                    },
                )
            }
            composable(
                route = Route.Details.route
            ) { backstackEntry ->

                val parentEntry = remember(backstackEntry) {
                    navController.getBackStackEntry(Route.List.route)
                }
                val viewModel: MoviesViewModel = hiltViewModel(parentEntry)
                val url = backstackEntry.getParameter("url")
                DetailsScreen(
                    url = url,
                    animatedVisibilityScope = this@composable,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxSize(),
                    moviesViewModel = viewModel
                )
            }
        }
    }
}

fun NavBackStackEntry.getParameter(key: String): String {
    val encoded = arguments?.getString(key) ?: error("No $key found")
    return URLDecoder.decode(encoded, "UTF-8")
}

fun String.utf8(): String {
    return URLEncoder.encode(this, "UTF-8")
}


