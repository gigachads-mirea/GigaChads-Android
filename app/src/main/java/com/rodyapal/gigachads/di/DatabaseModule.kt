package com.rodyapal.gigachads.di

import androidx.room.Room
import com.rodyapal.gigachads.model.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
	single {
		Room.databaseBuilder(
			androidContext(),
			AppDatabase::class.java,
			"gigachads.db"
		).build()
	}

	single { get<AppDatabase>().serverDao() }
	single { get<AppDatabase>().postDao() }
	single { get<AppDatabase>().commentDao() }
	single { get<AppDatabase>().gameDao() }
	single { get<AppDatabase>().userDao() }
}