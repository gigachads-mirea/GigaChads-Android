package com.rodyapal.gigachads.screens.favservers.subscreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.favservers.model.FavoriteServersScreenState
import com.rodyapal.gigachads.utils.MOCK_SERVER_BASIC_INFO

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteServersScreenDisplay(
	modifier: Modifier = Modifier,
	state: FavoriteServersScreenState.Display,
	onServerClick: (Long) -> Unit
) {
	LazyColumn(
		modifier = modifier.fillMaxSize()
	) {
		stickyHeader {
			Text(
				modifier = Modifier.padding(12.dp),
				text = stringResource(R.string.text_favorite_servers),
				style = MaterialTheme.typography.headlineMedium
			)

			Divider()
		}
		items(state.servers) {
			ServerItem(
				modifier = Modifier
					.padding(vertical = 8.dp, horizontal = 12.dp)
					.fillMaxWidth()
					.clickable {
						onServerClick(it.serverId)
					},
				serverName = it.serverName,
				gameName = it.gameName
			)
		}
	}
}

@Composable
private fun ServerItem(
	modifier: Modifier = Modifier,
	serverName: String,
	gameName: String
) {
	Column(
		modifier = modifier
	) {
		Text(
			text = gameName,
			style = MaterialTheme.typography.bodyMedium
		)

		Text(
			text = serverName,
			style = MaterialTheme.typography.titleMedium
		)
	}
}

@Preview
@Composable
private fun ServerItemPreview() {
	ServerItem(
		serverName = "Server name",
		gameName = "Game name"
	)
}

@Preview
@Composable
fun FavServersScreenPreview() {
	FavoriteServersScreenDisplay(
		state = FavoriteServersScreenState.Display(
			servers = MOCK_SERVER_BASIC_INFO
		),
		onServerClick = {}
	)
}