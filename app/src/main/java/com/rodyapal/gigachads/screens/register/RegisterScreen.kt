package com.rodyapal.gigachads.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.login.model.LoginScreenState
import com.rodyapal.gigachads.screens.register.model.RegisterScreenEvent
import com.rodyapal.gigachads.screens.register.model.RegisterScreenState
import com.rodyapal.gigachads.utils.TextFieldState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenState(
	onRegistered: () -> Unit,
	onAbortRegistration: () -> Unit,
	onError: () -> Unit,
	viewModel: RegisterScreenViewModel = koinViewModel()
) {
	val state = viewModel.viewState.collectAsState()
	if (state.value.isError) {
		onError()
	}
	if (state.value.showSuccessMessage) {
		onRegistered()
	}
	RegisterScreenDisplay(
		state = state.value,
		onEmailInput = {
			viewModel.reduce(
				RegisterScreenEvent.OnEmailInput(it)
			)
		},
		onUsernameInput = {
			viewModel.reduce(
				RegisterScreenEvent.OnUsernameInput(it)
			)
		},
		onPasswordInput = {
			viewModel.reduce(
				RegisterScreenEvent.OnPasswordInput(it)
			)
		},
		onConfirmInput = {
			viewModel.reduce(
				RegisterScreenEvent.OnConfirmInput(it)
			)
		},
		onRegisterClick = {
			viewModel.reduce(
				RegisterScreenEvent.OnAbortClick
			)
		},
		onAbortClick = {
			viewModel.reduce(
				RegisterScreenEvent.OnAbortClick
			)
			onAbortRegistration()
		},
		onPasswordVisibilityClick = {
			viewModel.reduce(
				RegisterScreenEvent.OnPasswordVisibilityClick
			)
		},
		onConfirmVisibilityClick = {
			viewModel.reduce(
				RegisterScreenEvent.OnConfirmVisibilityClick
			)
		}
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenDisplay(
	state: RegisterScreenState,
	onEmailInput: (String) -> Unit,
	onUsernameInput: (String) -> Unit,
	onPasswordInput: (String) -> Unit,
	onConfirmInput: (String) -> Unit,
	onRegisterClick: () -> Unit,
	onAbortClick: () -> Unit,
	onPasswordVisibilityClick: () -> Unit,
	onConfirmVisibilityClick: () -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp)
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		val focusManager = LocalFocusManager.current

		Text(
			modifier = Modifier.fillMaxWidth(),
			text = stringResource(R.string.text_sign_up),
			fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
			fontSize = MaterialTheme.typography.titleLarge.fontSize
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.email,
			onValueChange = onEmailInput,
			label = { Text(text = stringResource(R.string.text_email)) },
			isError = !state.emailState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Next,
				keyboardType = KeyboardType.Email
			)
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.username,
			onValueChange = onUsernameInput,
			label = { Text(text = stringResource(R.string.text_username)) },
			isError = !state.usernameState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Next,
				keyboardType = KeyboardType.Text
			)
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.password,
			onValueChange = onPasswordInput,
			label = { Text(text = stringResource(R.string.text_password)) },
			isError = !state.passwordState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Next,
				keyboardType = KeyboardType.Password
			),
			visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
			trailingIcon = {
				IconButton(
					onClick = onPasswordVisibilityClick
				) {
					Icon(
						painter =  painterResource(
							id = if (state.isPasswordVisible) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24
						),
						contentDescription = stringResource(R.string.text_show_password)
					)
				}
			}
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.confirm,
			onValueChange = onConfirmInput,
			label = { Text(text = stringResource(R.string.text_confirm_password)) },
			isError = !state.confirmState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onDone = { focusManager.clearFocus() }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done,
				keyboardType = KeyboardType.Password
			),
			visualTransformation = if (state.isConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
			trailingIcon = {
				IconButton(
					onClick = onConfirmVisibilityClick
				) {
					Icon(
						painter =  painterResource(
							id = if (state.isConfirmVisible) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24
						),
						contentDescription = stringResource(R.string.text_show_password)
					)
				}
			}
		)

		Spacer(modifier = Modifier.height(12.dp))

		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			Button(
				onClick = onRegisterClick
			) {
				Text(text = stringResource(R.string.text_sign_up))
			}

			OutlinedButton(
				onClick = onAbortClick
			) {
				Text(text = stringResource(R.string.text_abort))
			}
		}
	}
}

@Preview
@Composable
fun RegisterPreview() {
	RegisterScreenDisplay(
		RegisterScreenState(
			"", "", "", "",
			TextFieldState.UNTOUCHED, TextFieldState.UNTOUCHED, TextFieldState.UNTOUCHED, TextFieldState.UNTOUCHED,
			true, isConfirmVisible = true
		),
		{}, {}, {}, {}, {}, {}, {}, {}
	)
}