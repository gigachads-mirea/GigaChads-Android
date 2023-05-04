package com.rodyapal.gigachads.screens.serverposts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.model.entity.ServerWithPosts
import com.rodyapal.gigachads.screens.serverposts.model.PostsForServer
import com.rodyapal.gigachads.screens.serverposts.model.ServerPostsScreenState
import com.rodyapal.gigachads.ui.composable.PostCard
import com.rodyapal.gigachads.utils.MOCK_POSTS
import com.rodyapal.gigachads.utils.MOCK_SERVER

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServerPostsScreenDisplay(
	state: ServerPostsScreenState,
	onReadPostClick: (serverIndex: Int, postIndex: Int) -> Unit
) {
	LazyColumn(
		modifier = Modifier.fillMaxSize()
	) {
		stickyHeader {
			Column(
				modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
			) {
				Text(
					text = stringResource(R.string.text_posts),
					style = MaterialTheme.typography.headlineMedium,
					modifier = Modifier.padding(12.dp)
				)

				Divider()
			}
		}
		itemsIndexed(state.serversWithPosts) { serverIndex, (serverName, posts) ->
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(12.dp)
			) {
				Text(
					text = serverName,
					style = MaterialTheme.typography.bodyLarge,
				)

				Spacer(modifier = Modifier
					.height(4.dp)
				)

				LazyRow {
					itemsIndexed(posts) { postIndex, it ->
						PostCard(
							modifier = Modifier.fillParentMaxWidth(),
							post = it,
							serverName = "",
							onReadClick = {
								onReadPostClick(serverIndex, postIndex)
							}
						)
						Spacer(modifier = Modifier.width(8.dp))
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun ServerPostsScreenDisplayPreview() {
	ServerPostsScreenDisplay(
		state = ServerPostsScreenState(
			serversWithPosts = listOf(
				ServerWithPosts(
					MOCK_SERVER, MOCK_POSTS
				),
				ServerWithPosts(
					MOCK_SERVER, MOCK_POSTS
				),
				ServerWithPosts(
					MOCK_SERVER, MOCK_POSTS
				),
				ServerWithPosts(
					MOCK_SERVER, MOCK_POSTS
				),
			).map { PostsForServer.from(it) }
		),
		onReadPostClick = {_, _ -> }
	)
}