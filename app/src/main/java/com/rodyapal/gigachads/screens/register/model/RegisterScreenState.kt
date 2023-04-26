package com.rodyapal.gigachads.screens.register.model

import com.rodyapal.gigachads.utils.TextFieldState

data class RegisterScreenState(
	val email: String,
	val password: String,
	val confirm: String,
	val emailState: TextFieldState,
	val passwordState: TextFieldState,
	val confirmState: TextFieldState,
	val isPasswordVisible: Boolean,
	val isConfirmVisible: Boolean
)
