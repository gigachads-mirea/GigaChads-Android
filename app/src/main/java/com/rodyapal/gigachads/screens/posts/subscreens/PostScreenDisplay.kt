package com.rodyapal.gigachads.screens.posts.subscreens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.posts.model.PostScreenState
import com.rodyapal.gigachads.utils.MOCK_POSTS
import java.text.SimpleDateFormat

@Composable
fun PostScreenDisplay(
	state: PostScreenState.Display,
	onLikeClick: () -> Unit,
	onViewCommentsClick: () -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		Header(
			modifier = Modifier
				.fillMaxWidth()
				.padding(12.dp),
			serverName = state.serverName,
			postTitle = state.post.title,
			creationTime = SimpleDateFormat.getDateInstance().format(state.post.writtenAt),
		)

		Divider()

		PostBody(
			modifier = Modifier
				.fillMaxWidth()
				.padding(12.dp),
			content = state.post.content,
			likes = state.post.likes,
			isLikedByUser = state.isLikedByUser,
			onLikeClick = onLikeClick,
			onViewCommentsClick = onViewCommentsClick
		)
	}
}

@Composable
private fun Header(
	modifier: Modifier = Modifier,
	serverName: String,
	postTitle: String,
	creationTime: String,
) {
	Column(
		modifier = modifier
	) {
		Text(
			text = serverName,
			style = MaterialTheme.typography.bodyLarge
		)

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.Bottom
		) {
			Text(
				text = postTitle,
				style = MaterialTheme.typography.headlineLarge
			)

			Text(
				text = creationTime,
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.typography.bodyMedium.color.copy(
					alpha = 0.5f
				)
			)
		}
	}
}

@Composable
private fun PostBody(
	modifier: Modifier = Modifier,
	content: String,
	likes: Int,
	isLikedByUser: Boolean,
	onLikeClick: () -> Unit,
	onViewCommentsClick: () -> Unit,
) {
	Column(
		modifier = modifier
	) {
		Text(
			text = content,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Justify
		)

		Spacer(modifier = Modifier.height(4.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			OutlinedButton(
				onClick = onLikeClick,
			) {
				Crossfade(targetState = isLikedByUser) {
					Icon(
						imageVector = if (it) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
						contentDescription = stringResource(R.string.description_like_post)
					)
				}

				Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

				Text(
					text = likes.toString(),
				)
			}

			TextButton(
				onClick = onViewCommentsClick
			) {
				Text(text = stringResource(R.string.text_view_comments))
			}
		}
	}
}



@Preview
@Composable
private fun HeaderPreview() {
	Header(
		modifier = Modifier
			.fillMaxWidth()
			.padding(12.dp),
		serverName = "Server name",
		postTitle = "Post title",
		creationTime = SimpleDateFormat.getDateInstance().format(MOCK_POSTS[0].writtenAt),
	)
}

@Preview
@Composable
private fun PostBodyPreview() {
	var isLikedByUser by remember {
		mutableStateOf(false)
	}
	PostBody(
		modifier = Modifier
			.fillMaxWidth()
			.padding(12.dp),
		content = MOCK_POSTS[0].content,
		likes = MOCK_POSTS[0].likes,
		isLikedByUser = isLikedByUser,
		onLikeClick = {
			isLikedByUser = !isLikedByUser
		},
		onViewCommentsClick = {}
	)
}

@Preview
@Composable
private fun PostScreenDisplayPreview() {
	PostScreenDisplay(
		state = PostScreenState.Display(
			post = MOCK_POSTS[0],
			serverName = "Server name",
			isLikedByUser = false
		),
		onLikeClick = {},
		onViewCommentsClick = {}
	)
}