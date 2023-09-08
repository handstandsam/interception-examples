package com.handstandsam.interception.examples.delegates

import com.handstandsam.interception.examples.SystemOutLogger


object MainDelegateExample {

    @JvmStatic
    fun main(args: Array<String>) {
        val logger = (
            SystemOutLogger()
        )
        logger.log("Hello 1")
        logger.log("Hello 2")
        logger.error("Hello 3")

        //----------------------


        PassthroughDelegateLogger(logger)
        LoggingDelegateLogger(logger)
        ModifyingDatePrefixDelegatingLogger(logger)
        ShortCircuitNoErrorsDelegateLogger(logger)
    }
}

