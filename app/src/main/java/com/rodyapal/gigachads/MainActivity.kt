package com.rodyapal.gigachads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rodyapal.gigachads.screens.AppNavHost
import com.rodyapal.gigachads.ui.theme.GigachadsTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			GigachadsTheme {
				AppNavHost()
			}
		}
	}
}