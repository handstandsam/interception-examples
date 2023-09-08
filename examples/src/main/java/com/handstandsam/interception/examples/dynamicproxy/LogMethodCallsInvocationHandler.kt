package com.handstandsam.interception.examples.dynamicproxy

import com.handstandsam.interception.examples.Logger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogMethodCallsInvocationHandler(private val logger: Logger) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any? {
        println("----- Method ${method.name} -----")
        args?.forEachIndexed { index, any ->
            println(" * Parameter $index = $any")
        }

        val result = if (!args.isNullOrEmpty()) {
            method.invoke(logger, *args)
        } else {
            method.invoke(logger)
        }
        println("Result: $result")
        return result
    }
}