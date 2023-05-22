package com.rodyapal.gigachads.screens.search.model

sealed class SearchScreenEvent {
	object EnterScreen : SearchScreenEvent()
	object OnSearchClick : SearchScreenEvent()
	data class OnQueryChange(
		val query: String
	) : SearchScreenEvent()
	object OnAbortSearch : SearchScreenEvent()
	object OnClearInput : SearchScreenEvent()
	data class OnServerSelected(
		val serverId: Long
	) : SearchScreenEvent()
}