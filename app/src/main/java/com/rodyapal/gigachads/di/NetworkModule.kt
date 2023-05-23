package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.model.network.api.CommentApi
import com.rodyapal.gigachads.model.network.api.GameApi
import com.rodyapal.gigachads.model.network.api.PostApi
import com.rodyapal.gigachads.model.network.api.ServerApi
import com.rodyapal.gigachads.model.network.api.UserApi
import com.rodyapal.gigachads.model.network.ktorClient
import org.koin.dsl.module

val networkModule = module {
	single {
		CommentApi(ktorClient)
	}
	single {
		GameApi(ktorClient)
	}
	single {
		PostApi(ktorClient)
	}
	single {
		ServerApi(ktorClient)
	}
	single {
		UserApi(ktorClient)
	}
}