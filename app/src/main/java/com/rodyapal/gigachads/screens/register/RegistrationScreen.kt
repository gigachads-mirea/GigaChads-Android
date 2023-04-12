package com.rodyapal.gigachads.screens.register

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationScreen(
	onRegisterClick: (String, String, String, String) -> Unit,
	passwordValidator: (String) -> Boolean
) {
	var name by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }
	var phone by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }
	var isNameValid by remember { mutableStateOf(false) }
	var isEmailValid by remember { mutableStateOf(false) }
	var isPhoneValid by remember { mutableStateOf(false) }
	var isPasswordValid by remember { mutableStateOf(false) }
	var isConfirmPasswordValid by remember { mutableStateOf(false) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = "Register",
			style = MaterialTheme.typography.headlineMedium
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = name,
			onValueChange = { input ->
				name = input
				isNameValid = input.isNotBlank()
			},
			label = { Text("Name") },
			isError = !isNameValid,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = email,
			onValueChange = { input ->
				email = input
				isEmailValid = input.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(input).matches()
			},
			label = { Text("Email") },
			isError = !isEmailValid,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = phone,
			onValueChange = { input ->
				phone = input
				isPhoneValid = input.isNotBlank() && Patterns.PHONE.matcher(input).matches()
			},
			label = { Text("Phone Number") },
			isError = !isPhoneValid,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = password,
			onValueChange = { input ->
				password = input
				isPasswordValid = passwordValidator(input)
			},
			label = { Text("Password") },
			visualTransformation = PasswordVisualTransformation(),
			isError = !isPasswordValid,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = confirmPassword,
			onValueChange = { input ->
				confirmPassword = input
				isConfirmPasswordValid = input == password
			},
			label = { Text("Confirm Password") },
			visualTransformation = PasswordVisualTransformation(),
			isError = !isConfirmPasswordValid,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		Button(
			onClick = { onRegisterClick(name, email, phone, password) },
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Register")
		}
	}
}

@Preview
@Composable
fun RegistrationScreenDisplayPreview() {
	RegistrationScreen(
		onRegisterClick = {_,_,_,_ -> },
		passwordValidator = {
			it.isNotBlank() && it.length >= 6
		}
	)
}