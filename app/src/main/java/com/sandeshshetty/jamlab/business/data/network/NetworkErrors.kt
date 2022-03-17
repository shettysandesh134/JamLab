package com.sandeshshetty.jamlab.business.data.network

object NetworkErrors {

    const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
    const val UNABLE_TODO_OPERATION_WO_INTERNET = "Can't do that operation without an internet"
    const val ERROR_CHECK_NETWORK_CONNECTION = "Check network connection"
    const val NETWORK_ERROR_UNKNOWN = "Unknown network error"
    const val NETWORK_ERROR = "Network Error"
    const val NETWORK_ERROR_TIMEOUT = "Network timeout"
    const val NETWORK_DATA_NULL = "Network data is null"

    fun isNetworkError(msg: String): Boolean {
        when{
            msg.contains(UNABLE_TO_RESOLVE_HOST) -> return true
            else -> return false
        }
    }

}