package com.rodyapal.gigachads.model


interface Reducer <Event> {
	fun reduce(event: Event)
}