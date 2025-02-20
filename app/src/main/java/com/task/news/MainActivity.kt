package com.task.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.task.news.listing.presentation.state.ViewIntent
import com.task.news.listing.presentation.viewcomponents.DetailScreen
import com.task.news.listing.presentation.viewcomponents.HeadLinesList
import com.task.news.listing.presentation.viewmodels.TopHeadLineViewModel
import com.task.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsTheme {
                val navController = rememberNavController()
                var title by remember { mutableStateOf("") }
                var searchVisibility by remember { mutableStateOf(true) }

                val topHeadLineViewModel = hiltViewModel<TopHeadLineViewModel>()
                val headlines by topHeadLineViewModel.viewState.collectAsStateWithLifecycle()
                var search by remember { mutableStateOf("") }
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        CenterAlignedTopAppBar({
                            Text(text = title)
                        })
                        if (searchVisibility)
                            SearchBar(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                query = search,
                                active = false,
                                onActiveChange = {},
                                onQueryChange = {
                                    search = it
                                },
                                onSearch = {
                                    topHeadLineViewModel.handleViewIntent(
                                        ViewIntent.LoadHeadLines(
                                            1,
                                            search
                                        )
                                    )
                                },
                                content = {},
                                leadingIcon = {
                                    Image(
                                        painterResource(android.R.drawable.ic_menu_search),
                                        null
                                    )
                                })
                    }
                }) { innerPadding ->
                    NavHost(navController = navController, startDestination = Navigation.List) {
                        composable<Navigation.List> {
                            title = "HeadLines"
                            searchVisibility = true

                            LaunchedEffect(Unit) {
                                topHeadLineViewModel.handleViewIntent(
                                    ViewIntent.LoadHeadLines(
                                        1,
                                        ""
                                    )
                                )
                            }
                            HeadLinesList(
                                viewState = headlines,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            ) {

                                navController.navigate(
                                    Navigation.Details(
                                        it.image.orEmpty(),
                                        it.details.orEmpty()
                                    )
                                )
                            }
                        }
                        composable<Navigation.Details> {
                            val args = it.toRoute<Navigation.Details>()
                            title = "Details"
                            searchVisibility = false
                            DetailScreen(args.image, args.details)
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsTheme {

    }
}