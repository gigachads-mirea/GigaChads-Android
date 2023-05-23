package com.rodyapal.gigachads.screens.search.subscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import com.rodyapal.gigachads.screens.search.view.SearchBar
import com.rodyapal.gigachads.screens.search.view.ServerSearchItem

@Composable
fun SearchScreenDisplay(
	modifier: Modifier = Modifier,
	state: SearchScreenState.Display,
	onSearchClick: () -> Unit,
	onServerClicked: (Long) -> Unit
) {
	AnimatedVisibility(
		modifier = modifier.fillMaxSize(),
		visible = true,
		enter = fadeIn() + expandVertically(),
		exit = shrinkVertically() + fadeOut()
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			SearchBar(
				modifier = Modifier.padding(12.dp).fillMaxWidth(),
				label = stringResource(R.string.text_search_for_servers),
				onClick = onSearchClick
			)

			Spacer(modifier = Modifier.height(16.dp))

			if (state.searchHistory.isEmpty()) {
				Column(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center
				) {
					Icon(
						imageVector = Icons.Default.Info,
						contentDescription = stringResource(R.string.text_no_search_history),
						tint = LocalContentColor.current.copy(alpha = 0.75f)
					)

					Spacer(modifier = Modifier.height(4.dp))

					Text(
						text = stringResource(R.string.text_no_search_history),
						style = MaterialTheme.typography.bodyMedium,
					)
				}
			} else {
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
		}
	}
}