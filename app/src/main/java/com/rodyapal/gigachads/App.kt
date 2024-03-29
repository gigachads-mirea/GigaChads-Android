package com.rodyapal.gigachads

import android.app.Application
import com.rodyapal.gigachads.di.databaseModule
import com.rodyapal.gigachads.di.networkModule
import com.rodyapal.gigachads.di.repositoryModule
import com.rodyapal.gigachads.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@App)
			modules(
				databaseModule,
				repositoryModule,
				viewModelModule,
				networkModule
			)
		}
	}
}