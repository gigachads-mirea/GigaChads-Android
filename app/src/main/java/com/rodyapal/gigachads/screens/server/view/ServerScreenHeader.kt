package com.rodyapal.gigachads.screens.server.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import com.rodyapal.gigachads.utils.MOCK_SERVER_SCREEN_STATE

@Composable
fun ServerScreenHeader(
	modifier: Modifier = Modifier,
	state: ServerScreenState
) {
	Column(
		modifier = modifier
			.background(
				color = MaterialTheme.colorScheme.surfaceVariant,
				shape = RoundedCornerShape(
					bottomStart = 12.dp,
					bottomEnd = 12.dp
				)
			)
			.fillMaxWidth()
			.padding(12.dp),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.Start
	) {
		Text(
			text = state.server.name,
			style = MaterialTheme.typography.headlineLarge
		)

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = state.gameName,
			fontSize = MaterialTheme.typography.titleSmall.fontSize
		)

		Spacer(modifier = Modifier.height(4.dp))

		RatingBar(
			rating = state.server.rating
		)

		Spacer(modifier = Modifier.height(8.dp))

		Text(
			text = state.server.locale,
			style = MaterialTheme.typography.bodyMedium
		)

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = if (state.server.isModded) "Modded" else "Not modded",
			style = MaterialTheme.typography.bodyMedium
		)

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = if (state.server.hasAntiCheat) "Anti-cheat" else "No anti-cheat",
			style = MaterialTheme.typography.bodyMedium
		)
	}
}

@Preview
@Composable
fun ServerScreenHeaderPreview() {
	ServerScreenHeader(
		state = MOCK_SERVER_SCREEN_STATE
	)
}