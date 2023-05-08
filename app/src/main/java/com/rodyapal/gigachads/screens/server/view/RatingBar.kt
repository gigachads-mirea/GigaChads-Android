package com.rodyapal.gigachads.screens.server.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
	modifier: Modifier = Modifier,
	rating: Float = 0f,
	stars: Int = 5,
	filledStarColor: Color = MaterialTheme.colorScheme.primary,
	emptyStarColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f),
) {
	val filledStars = floor(rating).toInt()
	val unfilledStars = (stars - ceil(rating)).toInt()
	val halfStar = !(rating.rem(1).equals(0f))
	val halfFilledStarColor = filledStarColor.copy(rating - floor(rating))
	Row(modifier = modifier) {
		repeat(filledStars) {
			Icon(
				imageVector = Icons.Default.Star,
				contentDescription = null,
				tint = filledStarColor
			)
		}
		if (halfStar) {
			Icon(
				imageVector = Icons.Default.Star,
				contentDescription = null,
				tint = halfFilledStarColor
			)
		}
		repeat(unfilledStars) {
			Icon(
				imageVector = Icons.Outlined.Star,
				contentDescription = null,
				tint = emptyStarColor,
			)
		}
	}
}

@Preview
@Composable
fun RatingPreview() {
	RatingBar(rating = 2.8f)
}