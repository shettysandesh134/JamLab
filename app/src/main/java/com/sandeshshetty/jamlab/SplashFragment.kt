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
import com.sandeshshetty.jamlab.databinding.FragmentSplashBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class SplashFragment : Fragment() {

    val TAG = "SplashFragment"

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()

        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
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