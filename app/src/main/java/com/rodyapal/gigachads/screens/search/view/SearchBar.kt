package com.rodyapal.gigachads.screens.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
	modifier: Modifier = Modifier,
	label: String,
	onClick: () -> Unit
) {
	Surface(
		modifier = modifier.clickable { onClick() },
		color = MaterialTheme.colorScheme.surfaceVariant,
		shape = CircleShape,
		contentColor = MaterialTheme.colorScheme.onSurfaceVariant
	) {
		Row(
			modifier = Modifier.padding(12.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Start
		) {
			Icon(
				imageVector = Icons.Default.Search,
				contentDescription = label
			)

			Spacer(modifier = Modifier.width(12.dp))

			Text(
				text = label,
				fontSize = MaterialTheme.typography.titleMedium.fontSize
			)
		}
	}
}

@Preview
@Composable
fun SearchComponentPreview() {
	SearchBar(
		label = "Search here",
		modifier = Modifier
			.fillMaxWidth()
			.padding(12.dp)
	) {}
}