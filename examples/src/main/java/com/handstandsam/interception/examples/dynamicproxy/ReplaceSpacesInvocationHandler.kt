import com.handstandsam.interception.examples.Logger
import com.handstandsam.interception.examples.SystemOutLogger
import com.handstandsam.interception.examples.dynamicproxy.LoggingInvocationHandler
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object MyInvocationHandler {

    @JvmStatic
    fun main(args: Array<String>) {
        val realLogger = SystemOutLogger()
        val loggerProxyInstance = Proxy.newProxyInstance(
            object {}::class.java.classLoader,
            arrayOf<Class<*>>(
                Logger::class.java
            ),
            LoggingInvocationHandler(realLogger)
        ) as Logger
        realLogger.log("real log")
        loggerProxyInstance.log("proxy log")
        loggerProxyInstance.error("proxy error")
    }
}
