package com.handstandsam.interception.examples.dynamicproxy

import com.handstandsam.interception.examples.Logger
import com.handstandsam.interception.examples.SystemOutLogger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy


object MainDynamicProxyAddTimestamp {

    @JvmStatic
    fun main(args: Array<String>) {
        val logger = SystemOutLogger()

        logger.log("Hello 1")
        logger.log("Hello 2")
        logger.error("Hello 3")
        logger.hashCode()
        logger.toString()
        // --------------


        addInvocationHandler(
            LogMethodCallsInvocationHandler(SystemOutLogger())
        )

        addInvocationHandler(
            AddDateInvocationHandler(
                addInvocationHandler(
                    LogMethodCallsInvocationHandler(
                        SystemOutLogger()
                    )
                )
            )
        )

    }

    fun addInvocationHandler(invocationHandler: InvocationHandler): Logger {
        return Proxy.newProxyInstance(
            ClassLoader.getSystemClassLoader(),
            arrayOf(Logger::class.java),
            invocationHandler
        ) as Logger
    }
}



