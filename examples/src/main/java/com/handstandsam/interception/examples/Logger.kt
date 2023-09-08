package com.handstandsam.interception.examples

interface Logger {
    fun log(message: String)
    fun error(message: String)
}

class SystemOutLogger : Logger {
    override fun log(message: String) {
        println(message)
    }

    override fun error(message: String) {
        System.err.println(message)
    }
}