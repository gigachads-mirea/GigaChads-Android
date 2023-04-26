package com.rodyapal.gigachads.screens.search.model

import com.rodyapal.gigachads.model.entity.ServerBasicInfo

sealed class SearchScreenState {

	data class Display(
		val searchHistory: List<ServerBasicInfo>
	) : SearchScreenState()

	data class Search(
		val query: String,
		val suggestions: List<ServerBasicInfo>
	) : SearchScreenState()
}
