package com.rodyapal.gigachads.screens.serverposts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.model.entity.ServerWithPosts
import com.rodyapal.gigachads.screens.serverposts.model.PostsForServer
import com.rodyapal.gigachads.screens.serverposts.model.ServerPostsScreenEvent
import com.rodyapal.gigachads.screens.serverposts.model.ServerPostsScreenState
import com.rodyapal.gigachads.ui.composable.PostCard
import com.rodyapal.gigachads.utils.MOCK_POSTS
import com.rodyapal.gigachads.utils.MOCK_SERVER
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServerPostsScreen(
	onViewPost: (Long) -> Unit,
	viewModel: ServerPostsViewModel = koinViewModel(),
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(
			ServerPostsScreenEvent.EnterScreen
		)
	}
	when (val state = viewState.value) {
		is ServerPostsScreenState.Display -> ServerPostsScreenDisplay(
			state = state,
			onReadPostClick = { postId ->
				onViewPost(postId)
			}
		)
		ServerPostsScreenState.Loading -> Box(
			modifier = Modifier.fillMaxSize()
		) {
			CircularProgressIndicator(
				modifier = Modifier.align(Alignment.Center)
			)
		}
	}
}

@Composable
fun ServerPostsScreenDisplay(
	modifier: Modifier = Modifier,
	state: ServerPostsScreenState.Display,
	onReadPostClick: (Long) -> Unit
) {
	Column(
		modifier = modifier.fillMaxSize()
	) {
		Text(
			text = stringResource(R.string.text_posts),
			style = MaterialTheme.typography.headlineMedium,
			modifier = Modifier.padding(12.dp)
		)

		Divider()

		if (state.serversWithPosts.isEmpty()) {
			Column(
				modifier = Modifier.fillMaxSize(),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Icon(
					imageVector = Icons.Default.Info,
					contentDescription = stringResource(R.string.text_no_news),
					tint = LocalContentColor.current.copy(alpha = 0.75f)
				)

				Spacer(modifier = Modifier.height(4.dp))

				Text(
					text = stringResource(R.string.text_no_news),
					style = MaterialTheme.typography.bodyMedium,
				)
			}
		} else {
			LazyColumn(
				modifier = Modifier.fillMaxSize()
			) {
				items(state.serversWithPosts) { (serverName, posts) ->
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

						if (posts.isNotEmpty()) {
							LazyRow {
								items(posts) {
									PostCard(
										modifier = Modifier.fillParentMaxWidth(),
										post = it,
										serverName = "",
										onReadClick = {
											onReadPostClick(it.id)
										}
									)
									Spacer(modifier = Modifier.width(8.dp))
								}
							}
						} else {
							Text(
								text = stringResource(R.string.text_no_posts),
								style = MaterialTheme.typography.bodyMedium,
							)
						}
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
		state = ServerPostsScreenState.Display(
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
		onReadPostClick = {}
	)
}