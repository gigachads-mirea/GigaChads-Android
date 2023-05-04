package com.rodyapal.gigachads.screens.server.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import com.rodyapal.gigachads.ui.composable.PostCard
import com.rodyapal.gigachads.screens.server.view.ServerScreenHeader
import com.rodyapal.gigachads.utils.MOCK_SERVER_SCREEN_STATE_INFO

@Composable
fun ServerScreenInfo(
	state: ServerScreenState.Info,
	onAddToFavoriteClick: () -> Unit,
	onReadPostClicked: (Int) -> Unit,
	onSeeOtherPostsClick: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState()),
		verticalArrangement = Arrangement.SpaceBetween
	) {
		HeaderDescriptionPosts(
			state = state,
			onReadPostClicked = onReadPostClicked,
			onSeeOtherPostsClick = onSeeOtherPostsClick
		)

		ElevatedButton(
			modifier = Modifier.padding(12.dp),
			onClick = onAddToFavoriteClick
		) {
			Icon(
				imageVector = Icons.Default.Add,
				contentDescription = "Add server to favorite"
			)
			Text(text = "To favorite")
		}
	}
}

@Composable
private fun HeaderDescriptionPosts(
	modifier: Modifier = Modifier,
	state: ServerScreenState.Info,
	onReadPostClicked: (Int) -> Unit,
	onSeeOtherPostsClick: () -> Unit
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Top
	) {
		ServerScreenHeader(
			state = state
		)
		Column(
			Modifier
				.fillMaxWidth()
				.padding(12.dp)
		) {
			Text(
				text = "Description",
				style = MaterialTheme.typography.titleLarge
			)

			Spacer(modifier = Modifier.height(6.dp))

			Text(
				text = state.server.description,
				style = MaterialTheme.typography.bodyMedium
			)

			Spacer(modifier = Modifier.height(12.dp))

			TextButton(
				onClick = onSeeOtherPostsClick,
				contentPadding = PaddingValues(horizontal = 0.dp)
			) {
				Text(
					text = "Latest news",
					style = MaterialTheme.typography.titleLarge
				)

				Spacer(modifier = Modifier.width(8.dp))

				Icon(
					imageVector = Icons.Default.KeyboardArrowRight,
					contentDescription = "See other posts"
				)
			}

			Spacer(modifier = Modifier.height(4.dp))

			PostCard(
				modifier = Modifier.padding(end = 12.dp),
				post = state.latestPost,
				serverName = state.server.name,
				onReadClick = {
					onReadPostClicked(0)
				}
			)
		}
	}
}

@Preview
@Composable
fun ServerScreenInfoPreview() {
	ServerScreenInfo(
		state = MOCK_SERVER_SCREEN_STATE_INFO,
		onAddToFavoriteClick = {},
		onReadPostClicked = {},
		onSeeOtherPostsClick = {}
	)
}