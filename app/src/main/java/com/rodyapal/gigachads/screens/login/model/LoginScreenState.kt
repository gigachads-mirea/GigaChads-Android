package com.rodyapal.gigachads.screens.login.model

import com.rodyapal.gigachads.utils.TextFieldState

data class LoginScreenState(
	val email: String,
	val password: String,
	val emailState: TextFieldState,
	val passwordState: TextFieldState,
	val isPasswordVisible: Boolean
)