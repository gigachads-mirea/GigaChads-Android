package com.rodyapal.gigachads.screens.search.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.view.SearchBar
import com.rodyapal.gigachads.screens.search.view.ServerSearchItem

@Composable
fun SearchScreenDisplay(
	state: SearchScreenState.Display,
	onSearchClick: () -> Unit
) {
	SearchBar(
		label = "Search for servers",
		onClick = onSearchClick
	)

	LazyColumn(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.Start
	) {
		items(state.searchHistory.size) {
			ServerSearchItem(
				title = state.searchHistory[it].name,
				description = state.searchHistory[it].description
			)
		}
	}
}