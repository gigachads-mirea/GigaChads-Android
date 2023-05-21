package com.rodyapal.gigachads.screens.favservers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.rodyapal.gigachads.screens.favservers.model.FavoriteServersScreenEvent
import com.rodyapal.gigachads.screens.favservers.model.FavoriteServersScreenState
import com.rodyapal.gigachads.screens.favservers.subscreens.FavoriteServersScreenDisplay
import com.rodyapal.gigachads.screens.favservers.subscreens.FavoriteServersScreenEmpty
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteServers(
	onNavigateToServerScreen: (Long) -> Unit,
	viewModel: FavoriteServersViewModel = koinViewModel()
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(FavoriteServersScreenEvent.EnterScreen)
	}
	when (val state = viewState.value) {
		is FavoriteServersScreenState.Display -> FavoriteServersScreenDisplay(
			state = state,
			onServerClick = onNavigateToServerScreen
		)
		is FavoriteServersScreenState.Empty -> FavoriteServersScreenEmpty()
	}
}