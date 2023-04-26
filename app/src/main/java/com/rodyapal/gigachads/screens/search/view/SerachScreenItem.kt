package com.rodyapal.gigachads.screens.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ServerSearchItem(
	title: String,
	description: String
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalAlignment = Alignment.Start
	) {
		Text(
			text = title,
			fontSize = MaterialTheme.typography.labelLarge.fontSize
		)

		Spacer(modifier = Modifier.height(6.dp))

		Text(
			text = description,
			fontSize = MaterialTheme.typography.labelMedium.fontSize
		)
	}
}

@Preview
@Composable
fun ServerSearchItemPreview() {
	ServerSearchItem(
		title = "Server name",
		description = "Server short description"
	)
}