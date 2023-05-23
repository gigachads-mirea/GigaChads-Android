package com.rodyapal.gigachads.screens.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun SearchView(
	query: String,
	onQueryChange: (String) -> Unit,
	onQueryCompleted: (String) -> Unit,
	onNavigateBack: () -> Unit,
	onClearInput: () -> Unit,
	modifier: Modifier = Modifier,
	content: LazyListScope.() -> Unit
) {
	Column(modifier = modifier.fillMaxSize()) {
		Header(
			query = query,
			onQueryChange = onQueryChange,
			onQueryCompleted = onQueryCompleted,
			onNavigateBack = onNavigateBack,
			onClearInput = onClearInput
		)
		Container(
			modifier = Modifier.weight(1f),
			content = content
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun Header(
	query: String,
	onQueryChange: (String) -> Unit,
	onQueryCompleted: (String) -> Unit,
	onNavigateBack: () -> Unit,
	onClearInput: () -> Unit
) {
	val focusManager = LocalFocusManager.current
	LocalSoftwareKeyboardController.current?.show()
	val focusRequester = remember { FocusRequester() }
	val keyboard = LocalSoftwareKeyboardController.current
	LaunchedEffect(focusRequester) {
		focusRequester.requestFocus()
		delay(100)
		keyboard?.show()
	}
	LaunchedEffect(key1 = query) {
		if (query.isNotBlank()) {
			delay(500)
			onQueryCompleted(query)
		}
	}
	TextField(
		value = query,
		onValueChange = onQueryChange,
		placeholder = { Text("Search") },
		modifier = Modifier
			.fillMaxWidth()
			.focusRequester(focusRequester),
		leadingIcon = {
			IconButton(onClick = onNavigateBack) {
				Icon(Icons.Default.ArrowBack, contentDescription = "Back")
			}
		},
		trailingIcon = {
			if (query.isNotEmpty()) {
				IconButton(onClick = onClearInput) {
					Icon(Icons.Default.Clear, contentDescription = "Clear")
				}
			}
		},
		keyboardOptions = KeyboardOptions(
			imeAction = ImeAction.Done
		),
		keyboardActions = KeyboardActions(
			onDone = {
				focusManager.clearFocus()
			}
		),
		singleLine = true,
		colors = TextFieldDefaults.textFieldColors(
			containerColor = MaterialTheme.colorScheme.background,
			textColor = MaterialTheme.colorScheme.onBackground,
			cursorColor = MaterialTheme.colorScheme.onBackground,
			unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
			unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground
		)
	)
}

@Composable
fun Container(
	modifier: Modifier = Modifier,
	content: LazyListScope.() -> Unit
) {
	LazyColumn(modifier = modifier.fillMaxSize()) {
		content()
	}
}

@Preview
@Composable
fun SearchViewPreview() {
	SearchView(
		query = "",
		onQueryChange = {},
		onQueryCompleted = {},
		onNavigateBack = {},
		onClearInput = {},
		content = {
			items(5) {
				ServerSearchItem(title = "Title", description = "Description")
			}
		}
	)
}