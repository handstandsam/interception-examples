package com.handstandsam.interception.examples.delegates

import com.handstandsam.interception.examples.Logger
import java.util.Date

class ModifyingDatePrefixDelegatingLogger(
    private val delegate: Logger
) : Logger by delegate {
    override fun log(message: String) {
        delegate.log("${Date()}: $message")
    }
}