package com.sandeshshetty.jamlab

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import kotlin.system.measureTimeMillis


// This Activity is not used
class SplashActivity: AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        activityScope.launch {
//            delay(6000)
//            var intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }



}