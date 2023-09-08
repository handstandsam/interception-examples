package com.handstandsam.interception.examples.dynamicproxy

import CLASS_LOADER
import com.handstandsam.interception.examples.Logger
import com.handstandsam.interception.examples.SystemOutLogger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object Brainstorming {

    @JvmStatic
    fun main(args: Array<String>) {

        SystemOutLogger().also { logger ->
            logger.log("Hello 1")
        }
        ProxyFactory.replaceSpacesLoggerProxy(SystemOutLogger())
            .also { logger ->
                logger.log("Hello 4")
            }

        val origList = mutableListOf<Int>().withInterceptor(MutableList::class.java) { subject, method, args ->
            println("Always adding 100, even though method is ${method.name}")
            subject.add(100)
            println("$subject")

        }

        origList.add(1)
        origList.add(2)
        origList.clear()
        origList.add(3)
    }


    class ReplaceSpacesInvocationHandler(private val logger: Logger) : InvocationHandler {
        override fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any? {
            return if (args != null) {
                if (method.name == "log") {
                    method.invoke(logger, (args[0] as String?)?.replace(" ", "_"))
                }
                method.invoke(logger, *args)
            } else {
                method.invoke(logger)
            }
        }
    }

    class InterceptAndLogInvocationHandler<T>(private val real: T) : InvocationHandler {

        override fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any? {
            println("${this::class.java.simpleName} method=${method.name} args=${args?.map { arg -> arg!!::class.java.name + " " + arg.toString() }}")
            return if (args != null) {
                method.invoke(real, *args)
            } else {
                method.invoke(real)
            }
        }
    }

    class ProxyFactory {
        companion object {
            fun replaceSpacesLoggerProxy(real: Logger): Logger {
                return Proxy.newProxyInstance(
                    object {}::class.java.classLoader,
                    arrayOf(Logger::class.java),
                    ReplaceSpacesInvocationHandler(real)
                ) as Logger
            }

            inline fun <reified T : Any> retrieveSomething2(snapshot: T): T? {
                return T::class.java.cast(snapshot)
            }


            inline fun <reified T : Any> retrieveSomething(snapshot: T): T {
                return Proxy.newProxyInstance(
                    CLASS_LOADER,
                    arrayOf(T::class.java),
                    InterceptAndLogInvocationHandler(snapshot)
                ) as T
            }

            fun <T> interceptingProxy(real: Any, interfaceClass: Class<*>, lambda: (T) -> T): T {
                return Proxy.newProxyInstance(
                    CLASS_LOADER,
                    arrayOf(interfaceClass),
                    InterceptAndLogInvocationHandler(real)
                ) as T
            }

        }
    }

    fun <T> T.withInterceptor(
        interfaceClass: Class<*>,
        before: (subject: T, method: Method, args: List<Any?>) -> Unit,
    ): T {
        val subject = this
        val proxyInstance = Proxy.newProxyInstance(
            CLASS_LOADER,
            arrayOf(interfaceClass)
        ) { _, method, args ->
            before(subject, method, args?.toList() ?: listOf())
            if (args != null) {
                method.invoke(subject, *args)
            } else {
                method.invoke(subject)
            }
        } as T
        return proxyInstance
    }

    private fun <T> T.withInvokingInterceptor(
        interfaceClass: Class<*>,
        before: (subject: T, method: Method, args: List<Any?>) -> Unit,
    ): T {
        val subject = this
        val proxyInstance = Proxy.newProxyInstance(
            object {}::class.java.classLoader,
            arrayOf(interfaceClass)
        ) { _, method, args ->
            before(subject, method, args?.toList() ?: listOf())
            if (args != null) {
                method.invoke(subject, *args)
            } else {
                method.invoke(subject)
            }
        } as T
        return proxyInstance
    }


}


