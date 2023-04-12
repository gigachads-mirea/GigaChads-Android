package com.rodyapal.gigachads.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
	onLoginClick: (String, String) -> Unit,
	onRegisterClick: () -> Unit
) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = "Login",
			style = MaterialTheme.typography.headlineMedium
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = email,
			onValueChange = { email = it },
			label = { Text("Email") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = password,
			onValueChange = { password = it },
			label = { Text("Password") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			OutlinedButton(
				onClick = onRegisterClick,
				modifier = Modifier.fillMaxWidth(.45f)
			) {
				Text(text = "Register")
			}

			Button(
				onClick = { onLoginClick(email, password) },
				modifier = Modifier.fillMaxWidth(.8f)
			) {
				Text(text = "Log in")
			}
		}
	}
}

@Preview
@Composable
fun LoginScreenDisplayPreview() {
	LoginScreen(onLoginClick = {_,_ ->}, onRegisterClick = {})
}