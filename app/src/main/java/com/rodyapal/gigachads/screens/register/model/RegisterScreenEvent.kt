package com.rodyapal.gigachads.screens.register.model

sealed class RegisterScreenEvent {
	data class OnEmailInput(
		val input: String,
	) : RegisterScreenEvent()
	data class OnUsernameInput(
		val input: String,
	) : RegisterScreenEvent()
	data class OnPasswordInput(
		val input: String,
	) : RegisterScreenEvent()
	data class OnConfirmInput(
		val input: String,
	) : RegisterScreenEvent()
	object OnRegisterClick : RegisterScreenEvent()
	object OnAbortClick : RegisterScreenEvent()
	object OnPasswordVisibilityClick : RegisterScreenEvent()
	object OnConfirmVisibilityClick : RegisterScreenEvent()
}
