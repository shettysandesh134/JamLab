package com.sandeshshetty.jamlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.sandeshshetty.jamlab.onBoarding.screens.FirstScreen
import com.sandeshshetty.jamlab.onBoarding.screens.SecondScreen
import com.sandeshshetty.jamlab.onBoarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        view.tvNext.setOnClickListener {
            view.viewPager.currentItem = view.viewPager.currentItem +1
        }

        view.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_signInFragment)
        }


        return view
    }



}