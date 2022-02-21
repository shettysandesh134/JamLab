package com.sandeshshetty.jamlab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.databinding.FragmentSignInBinding
import com.sandeshshetty.jamlab.databinding.FragmentSplashBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        findNavController().navigate(R.id.action_signInFragment_to_usersLocation)

        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }
}