package com.rodyapal.gigachads.screens.post.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.rodyapal.gigachads.screens.post.model.PostScreenState
import com.rodyapal.gigachads.utils.MOCK_COMMENTS
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreenComments(
	modifier: Modifier = Modifier,
	state: PostScreenState.Comments,
	onUserCommentInput: (String) -> Unit,
	onUserCommentDone: () -> Unit,
	onBackPressed: () -> Unit
) {
	Column(
		modifier = modifier.fillMaxSize(),
		verticalArrangement = Arrangement.SpaceBetween
	) {
		Column {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				IconButton(onClick = onBackPressed) {
					Icon(
						imageVector = Icons.Default.ArrowBack,
						contentDescription = stringResource(R.string.description_back_to_post)
					)
				}

				Spacer(modifier = Modifier.width(16.dp))

				Text(
					modifier = Modifier.padding(12.dp),
					text = state.postTitle,
					style = MaterialTheme.typography.headlineLarge
				)
			}
			Divider()
			LazyColumn(
				modifier = Modifier.fillMaxWidth()
			) {
				items(state.comments) { comment ->
					Comment(
						modifier = Modifier.padding(12.dp),
						comment = comment,
					)
				}
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
					style = MaterialTheme.typography.bodySmall,
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
	)
}

@Preview
@Composable
private fun PostScreenCommentsPreview() {
	PostScreenComments(
		state = PostScreenState.Comments(
			comments = MOCK_COMMENTS,
			userComment = "",
			postTitle = "First post"
		),
		onUserCommentInput = {},
		onUserCommentDone = {},
		onBackPressed = {}
	)
}