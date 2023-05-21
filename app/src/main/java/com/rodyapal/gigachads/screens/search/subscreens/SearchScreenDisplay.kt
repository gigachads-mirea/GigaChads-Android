package com.rodyapal.gigachads.screens.search.subscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.view.SearchBar
import com.rodyapal.gigachads.screens.search.view.ServerSearchItem

@Composable
fun SearchScreenDisplay(
	state: SearchScreenState.Display,
	onSearchClick: () -> Unit,
	onServerClicked: (Long) -> Unit
) {
	SearchBar(
		label = stringResource(R.string.text_search_for_servers),
		onClick = onSearchClick
	)

	LazyColumn(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.Start
	) {
		items(state.searchHistory) {
			ServerSearchItem(
				modifier = Modifier.clickable {
					onServerClicked(it.serverId)
				},
				title = it.serverName,
				description = it.gameName
			)
		}
	}
}