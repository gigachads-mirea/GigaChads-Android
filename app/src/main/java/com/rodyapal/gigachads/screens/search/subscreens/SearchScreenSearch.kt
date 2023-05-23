package com.rodyapal.gigachads.screens.search.subscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.view.SearchView
import com.rodyapal.gigachads.screens.search.view.ServerSearchItem

@Composable
fun SearchScreenSearch(
	state: SearchScreenState.Search,
	onQueryChange: (String) -> Unit,
	onQueryCompleted: (String) -> Unit,
	onBackPressed: () -> Unit,
	onClearInput: () -> Unit,
	onServerClicked: (Long) -> Unit,
) {
	AnimatedVisibility(
		visible = true,
		enter = fadeIn() + expandVertically(),
		exit = shrinkVertically() + fadeOut()
	) {
		SearchView(
			query = state.query,
			onQueryChange = onQueryChange,
			onQueryCompleted = onQueryCompleted,
			onNavigateBack = onBackPressed,
			onClearInput = onClearInput,
		) {
			items(state.suggestions) {
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
}