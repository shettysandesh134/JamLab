package com.sandeshshetty.jamlab.ui.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandeshshetty.jamlab.R


class FirstScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first_screen, container, false)

//        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
//
//        view.textView4.setOnClickListener {
//            viewPager?.currentItem = 1
//        }

        return view
    }
}