package com.sandeshshetty.jamlab

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class SplashFragment : Fragment() {

    val TAG = "SplashFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//            GlobalScope.launch(Dispatchers.IO) {
//                val time = measureTimeMillis {
//
////                    var answer1: String? = null
////                    var answer2: String? = null
////
////                    val job1 = launch { answer1 = job1() }
////                    val job2 = launch { answer2 = job1() }
////                   job1.join()
////                    job2.join()
//
//                    val answer1 = async { job1() }
//                    val answer2 = async { job2() }
//
//                    Log.d(TAG, "The answer is ${answer1.await()}")
//                    Log.d(TAG, "The answer is ${answer2.await()}")
//            }
//                Log.d(TAG, "The time is $time ms")
//        }


        (activity as AppCompatActivity?)!!.supportActionBar?.hide()

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
    }

//    suspend fun job1():String{
//        delay(3000L)
//       return "Answer1"
//    }
//
//    suspend fun job2():String{
//        delay(3000L)
//        return "Answer2"
//    }

}