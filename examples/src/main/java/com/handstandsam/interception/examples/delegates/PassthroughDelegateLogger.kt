package com.handstandsam.interception.examples.delegates

import com.handstandsam.interception.examples.Logger

class PassthroughDelegateLogger(
    private val delegate: Logger
) : Logger {
    override fun log(message: String) {
        delegate.log(message)
    }

    override fun error(message: String) {
        delegate.error(message)
    }
}