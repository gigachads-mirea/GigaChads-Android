package com.rodyapal.gigachads.screens.favservers.subscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R

@Composable
fun FavoriteServersScreenEmpty(
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier.fillMaxSize()
	) {
		Text(
			text = stringResource(R.string.text_favorite_servers),
			style = MaterialTheme.typography.headlineMedium
		)

		Divider()

		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				imageVector = Icons.Default.Info,
				contentDescription = stringResource(R.string.text_no_fav_servers)
			)

			Spacer(modifier = Modifier.height(4.dp))

			Text(
				text = stringResource(R.string.text_no_fav_servers),
				style = MaterialTheme.typography.titleMedium
			)
		}
	}
}

@Preview
@Composable
private fun FavServersScreenEmptyPreview() {
	FavoriteServersScreenEmpty()
}