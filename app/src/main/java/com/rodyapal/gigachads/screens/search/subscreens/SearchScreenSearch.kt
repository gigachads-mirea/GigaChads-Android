package com.rodyapal.gigachads.screens.search.subscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.view.SearchView
import com.rodyapal.gigachads.screens.search.view.ServerSearchItem

@Composable
fun SearchScreenSearch(
	state: SearchScreenState.Search,
	onQueryChange: (String) -> Unit,
	onBackPressed: () -> Unit,
	onClearInput: () -> Unit
) {
	AnimatedVisibility(
		visible = true,
		enter = fadeIn() + expandVertically(),
		exit = shrinkVertically() + fadeOut()
	) {
		SearchView(
			query = state.query,
			onQueryChange = onQueryChange,
			onNavigateBack = onBackPressed,
			onClearInput = onClearInput,
		) {
			items(state.suggestions.size) {
				ServerSearchItem(
					title = state.suggestions[it].serverName,
					description = state.suggestions[it].gameName
				)
			}
		}
	}
}