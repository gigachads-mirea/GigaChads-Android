package com.rodyapal.gigachads.screens.post.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.post.model.PostScreenState
import com.rodyapal.gigachads.utils.MOCK_POSTS
import java.text.SimpleDateFormat

@Composable
fun PostScreenDisplay(
	modifier: Modifier = Modifier,
	state: PostScreenState.Display,
	onViewCommentsClick: () -> Unit,
) {
	Column(
		modifier = modifier
			.fillMaxSize()
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
				.fillMaxSize()
				.padding(12.dp),
			content = state.post.content,
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

		Text(
			text = postTitle,
			style = MaterialTheme.typography.headlineLarge
		)

		Text(
			text = creationTime,
			style = MaterialTheme.typography.bodyMedium,
		)
	}
}

@Composable
private fun PostBody(
	modifier: Modifier = Modifier,
	content: String,
	onViewCommentsClick: () -> Unit,
) {
	Column(
		modifier = modifier.verticalScroll(rememberScrollState()),
		verticalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = content,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Justify
		)

		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.End
		) {
			Spacer(modifier = Modifier.height(4.dp))

			TextButton(
				onClick = onViewCommentsClick,
				contentPadding = PaddingValues(end = 0.dp)
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
	PostBody(
		modifier = Modifier
			.fillMaxWidth()
			.padding(12.dp),
		content = MOCK_POSTS[0].content,
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
		),
		onViewCommentsClick = {}
	)
}