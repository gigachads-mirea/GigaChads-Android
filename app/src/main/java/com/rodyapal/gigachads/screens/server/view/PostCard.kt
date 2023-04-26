package com.rodyapal.gigachads.screens.server.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.utils.MOCK_POSTS

@Composable
fun PostCard(
	modifier: Modifier = Modifier,
	post: Post,
	serverName: String,
	onReadClick: () -> Unit
) {
	OutlinedCard(
		modifier = modifier
	) {
		Column(
			modifier = Modifier
				.padding(12.dp),
			horizontalAlignment = Alignment.End
		) {
			Text(
				modifier = Modifier.fillMaxWidth(),
				text = post.title,
				style = MaterialTheme.typography.titleLarge,
				textAlign = TextAlign.Start
			)
			Text(
				modifier = Modifier.fillMaxWidth(),
				text = serverName,
				textAlign = TextAlign.Start
			)

			Spacer(modifier = Modifier.height(8.dp))

			Text(
				modifier = Modifier.fillMaxWidth(),
				text = post.content,
				style = MaterialTheme.typography.bodyMedium,
				maxLines = 3,
				textAlign = TextAlign.Start,
				overflow = TextOverflow.Ellipsis,
			)

			Spacer(modifier = Modifier.height(12.dp))

			FilledTonalButton(
				onClick = onReadClick
			) {
				Text(text = "Read full")
			}
		}
	}
}

@Preview
@Composable
fun PostCardPreview() {
	PostCard(
		modifier = Modifier.padding(12.dp),
		post = MOCK_POSTS[0],
		serverName = "Generic server",
		onReadClick = {}
	)
}