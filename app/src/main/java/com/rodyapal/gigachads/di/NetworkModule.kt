package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.model.network.ApiClient
import com.rodyapal.gigachads.model.network.ApiMockEngine
import com.rodyapal.gigachads.model.network.api.COMMENT_API_URL
import com.rodyapal.gigachads.model.network.api.CommentApi
import com.rodyapal.gigachads.model.network.api.GAME_API_URL
import com.rodyapal.gigachads.model.network.api.GameApi
import com.rodyapal.gigachads.model.network.api.POST_API_URL
import com.rodyapal.gigachads.model.network.api.PostApi
import com.rodyapal.gigachads.model.network.api.SERVER_API_URL
import com.rodyapal.gigachads.model.network.api.ServerApi
import com.rodyapal.gigachads.model.network.api.USER_API_URL
import com.rodyapal.gigachads.model.network.api.UserApi
import org.koin.dsl.module

val networkModule = module {
	single { ApiClient(ApiMockEngine().get()) }
	single { CommentApi(get<ApiClient>().client, COMMENT_API_URL) }
	single { GameApi(get<ApiClient>().client, GAME_API_URL) }
	single { PostApi(get<ApiClient>().client, POST_API_URL) }
	single { ServerApi(get<ApiClient>().client, SERVER_API_URL) }
	single { UserApi(get<ApiClient>().client, USER_API_URL) }
}