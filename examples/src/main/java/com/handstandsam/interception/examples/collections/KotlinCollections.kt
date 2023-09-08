package com.handstandsam.interception.examples.collections

import kotlinx.coroutines.runBlocking

object KotlinCollections {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        listOf(1, 2, 3)
//            .onEach { println("Logged $it") }
//            .map { it + 100 }
//            .onEach { println("Collected $it") }
        Unit
    }
}