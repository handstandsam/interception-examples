package com.handstandsam.interception.examples.dynamicproxy

import com.handstandsam.interception.examples.Logger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.util.Date

class AddDateInvocationHandler(private val logger: Logger) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any? {
        return when (method.name) {
            "log" -> {
                val newLoggingMessage = "${Date()} ${args!![0]}"
                method.invoke(logger, newLoggingMessage)
            }

            else -> {
                if (!args.isNullOrEmpty()) {
                    method.invoke(logger, *args)
                } else {
                    method.invoke(logger)
                }
            }
        }
    }
}