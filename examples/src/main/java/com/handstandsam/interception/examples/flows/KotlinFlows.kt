package com.handstandsam.interception.examples.flows

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

object KotlinFlows {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        flowOf(1, 2, 3)
//            .onEach { println("Logged $it") }
//            .map { it + 100 }
//            .onEach { println("Collected $it") }
            .collect()
    }
}