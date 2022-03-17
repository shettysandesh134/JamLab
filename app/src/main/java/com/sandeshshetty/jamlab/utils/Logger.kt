package com.sandeshshetty.jamlab.utils

import android.util.Log
import com.sandeshshetty.jamlab.utils.Constants.DEBUG
import com.sandeshshetty.jamlab.utils.Constants.TAG

var isUnitTest = false

fun printLogD(className: String?, message: String) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if (DEBUG && isUnitTest) {
        println("$className: $message")   // Since Log.d cant be used in Unit Test
    }
}

// function for logging crashlytics
fun cLog(msg: String?) {
    msg?.let {
        if (!DEBUG) {
//            FirebaseCrashlytics.getInstance().log(it)
        }
    }
}