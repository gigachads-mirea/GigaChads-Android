package com.rodyapal.gigachads.screens.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchView(
	query: String,
	onQueryChange: (String) -> Unit,
	onNavigateBack: () -> Unit,
	onClearInput: () -> Unit,
	modifier: Modifier = Modifier,
	content: LazyListScope.() -> Unit
) {
	Column(modifier = modifier.fillMaxSize()) {
		Header(
			query = query,
			onQueryChange = onQueryChange,
			onNavigateBack = onNavigateBack,
			onClearInput = onClearInput
		)
		Container(
			modifier = Modifier.weight(1f),
			content = content
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(
	query: String,
	onQueryChange: (String) -> Unit,
	onNavigateBack: () -> Unit,
	onClearInput: () -> Unit
) {
	TextField(
		value = query,
		onValueChange = onQueryChange,
		placeholder = { Text("Search") },
		modifier = Modifier.fillMaxWidth(),
		leadingIcon = {
			IconButton(onClick = onNavigateBack) {
				Icon(Icons.Default.ArrowBack, contentDescription = "Back")
			}
		},
		trailingIcon = {
			IconButton(onClick = onClearInput) {
				Icon(Icons.Default.Clear, contentDescription = "Clear")
			}
		},
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
		onNavigateBack = {},
		onClearInput = {},
		content = {
			items(5) {
				Column(
					modifier = Modifier.padding(16.dp),
					horizontalAlignment = Alignment.Start
				) {
					Text(
						text = "Title text",
						fontSize = MaterialTheme.typography.labelMedium.fontSize
					)
					Text(
						text = "Support text",
						fontSize = MaterialTheme.typography.labelSmall.fontSize
					)
				}
			}
		}
	)
}