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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.screens.posts.model.PostScreenState
import com.rodyapal.gigachads.utils.MOCK_COMMENTS
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreenComments(
	state: PostScreenState.Comments,
	onCommentLike: (Int) -> Unit,
	onUserCommentInput: (String) -> Unit,
	onUserCommentDone: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.SpaceBetween
	) {
		LazyColumn(
			modifier = Modifier.fillMaxWidth()
		) {
			itemsIndexed(state.comments) {index, (comment, isCommentLicked) ->
				Comment(
					modifier = Modifier.padding(12.dp),
					comment = comment,
					isCommentLiked = isCommentLicked,
					onCommentLike = {
						onCommentLike(index)
					}
				)
			}
		}

		val focusManager = LocalFocusManager.current
		TextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.userComment,
			onValueChange = onUserCommentInput,
			placeholder = {
				stringResource(id = R.string.description_confirm_comment)
			},
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done
			),
			keyboardActions = KeyboardActions(
				onDone = {
					focusManager.clearFocus()
					onUserCommentDone()
				}
			),
			trailingIcon = {
				IconButton(onClick = onUserCommentDone) {
					Icon(
						imageVector = Icons.Default.Done,
						contentDescription = stringResource(id = R.string.description_confirm_comment)
					)
				}
			}
		)
	}
}

@Composable
private fun Comment(
	modifier: Modifier = Modifier,
	comment: Comment,
	isCommentLiked: Boolean,
	onCommentLike: () -> Unit,
) {
	Column(
		modifier = modifier,
	) {
		Text(
			text = comment.authorName,
			style = MaterialTheme.typography.titleMedium
		)

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column {
				Text(
					text = comment.text,
					style = MaterialTheme.typography.bodyMedium
				)

				Spacer(modifier = Modifier.height(4.dp))

				Text(
					text = SimpleDateFormat.getDateInstance().format(comment.writtenAt),
					style = MaterialTheme.typography.bodyMedium,
					color = MaterialTheme.typography.bodyMedium.color.copy(
						alpha = 0.5f
					)
				)
			}

			TextButton(
				onClick = onCommentLike,
			) {
				Crossfade(targetState = isCommentLiked) {
					Icon(
						imageVector = if (it) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
						contentDescription = stringResource(R.string.description_like_post)
					)
				}

				Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

				Text(
					text = comment.likes.toString(),
				)
			}
		}
	}
}

@Preview
@Composable
private fun CommentPreview() {
	var isLikedByUser by remember {
		mutableStateOf(false)
	}
	Comment(
		modifier = Modifier.padding(12.dp),
		comment = MOCK_COMMENTS[0],
		isCommentLiked = isLikedByUser,
		onCommentLike = {
			isLikedByUser = !isLikedByUser
		}
	)
}

@Preview
@Composable
private fun CommentsPreview() {
	LazyColumn {
		itemsIndexed(MOCK_COMMENTS) { index, it ->
			Comment(
				modifier = Modifier.padding(12.dp),
				comment = it,
				isCommentLiked = false,
				onCommentLike = {}
			)
		}
	}
}

@Preview
@Composable
private fun PostScreenCommentsPreview() {
	PostScreenComments(
		state = PostScreenState.Comments(
			comments = MOCK_COMMENTS.map { it to false },
			userComment = "",
		),
		onCommentLike = {},
		onUserCommentInput = {},
		onUserCommentDone = {}
	)
}