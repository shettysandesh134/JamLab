package com.sandeshshetty.jamlab.business.domain.state

interface StateEvent {

    fun errorInfo(): String

    fun eventName(): String

    fun shouldDisplayProgressbar(): Boolean
}