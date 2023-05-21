package com.rodyapal.gigachads.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.rodyapal.gigachads.screens.search.model.SearchScreenEvent
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.subscreens.SearchScreenDisplay
import com.rodyapal.gigachads.screens.search.subscreens.SearchScreenSearch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
	onNavigateToServer: (Long) -> Unit,
	viewModel: SearchViewModel = koinViewModel()
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(
			SearchScreenEvent.EnterScreen
		)
	}
	when (val state = viewState.value) {
		is SearchScreenState.Display -> {
			SearchScreenDisplay(
				state = state,
				onSearchClick = {
					viewModel.reduce(
						SearchScreenEvent.OnSearchClick
					)
				},
				onServerClicked = {
					viewModel.reduce(
						SearchScreenEvent.EnterScreen
					)
					onNavigateToServer(it)
				}
			)
		}
		is SearchScreenState.Search -> {
			SearchScreenSearch(
				state = state,
				onQueryChange = {
					viewModel.reduce(
						SearchScreenEvent.OnQueryChange(it)
					)
				},
				onBackPressed = {
					viewModel.reduce(
						SearchScreenEvent.OnAbortSearch
					)
				},
				onClearInput = {
					viewModel.reduce(
						SearchScreenEvent.OnClearInput
					)
				},
				onServerClicked = {
					viewModel.reduce(
						SearchScreenEvent.EnterScreen
					)
					onNavigateToServer(it)
				},
			)
		}
	}
}

