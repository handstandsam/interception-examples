package com.handstandsam.interception.examples.dynamicproxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LoggingInvocationHandler<T>(private val real: T) : InvocationHandler {

        @Throws(Throwable::class)
        override operator fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any? {
            println("Method ${method.name}")
            args?.forEachIndexed { index, any ->
                println(" * Parameter $index = $any")
            }
            val argsArray: Array<Any?>? = args ?: arrayOf()
            return if (!argsArray.isNullOrEmpty()) {
                method.invoke(real, args)
            } else {
                method.invoke(real)
            }
        }
    }