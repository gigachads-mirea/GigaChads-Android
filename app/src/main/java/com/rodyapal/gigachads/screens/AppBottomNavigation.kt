package com.rodyapal.gigachads.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun BottomNav(
	modifier: Modifier = Modifier,
	navController: NavController
) {
	var selectedScreenRoute by remember {
		mutableStateOf(Screen.homeScreen.route)
	}
	NavigationBar(
		modifier = modifier,
	) {
		Screen.bottomNavScreens.forEach {
			NavigationBarItem(
				selected = it.route == selectedScreenRoute,
				onClick = {
					selectedScreenRoute = it.route
					navController.navigate(it.route)
				},
				icon = {
					Icon(
						painter = painterResource(id = (it as BottomNavScreen).iconId),
						contentDescription = stringResource(id = (it as BottomNavScreen).contentDescription)
					)
				},
				label = {
					Text(text = stringResource(id = it.labelId))
				},
				alwaysShowLabel = false
			)
		}
	}
}