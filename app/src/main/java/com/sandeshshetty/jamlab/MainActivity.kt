package com.sandeshshetty.jamlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //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

    @Inject lateinit var hiltString: String
    @Inject lateinit var medicalService: MedicalService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Log.d("MainActivity", hiltString)

//        CoroutineScope(IO).launch {
//            medicalService.login()
//        }
    }
}