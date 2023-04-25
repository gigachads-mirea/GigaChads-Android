package com.rodyapal.gigachads.utils

enum class TextFieldState {
	VALID, INVALID, UNTOUCHED;

	fun isValidOrUntouched(): Boolean = this != INVALID

	fun isValid(): Boolean = this == VALID
	companion object {
		fun from(bool: Boolean) = if (bool) VALID else INVALID
	}
}