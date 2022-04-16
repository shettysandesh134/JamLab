package com.sandeshshetty.jamlab.framework.presentation.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.databinding.FragmentViewPagerBinding
import com.sandeshshetty.jamlab.framework.presentation.onBoarding.screens.FirstScreen
import com.sandeshshetty.jamlab.framework.presentation.onBoarding.screens.SecondScreen
import com.sandeshshetty.jamlab.framework.presentation.onBoarding.screens.ThirdScreen
import com.sandeshshetty.jamlab.utils.Constants.FIRST_TIME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingViewPagerFragment : Fragment() {

    @Inject
    lateinit var dataStoreRepository: DataStoreRepository

    private var _binding: FragmentViewPagerBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =   FragmentViewPagerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        binding.tvNext.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem +1
        }

        binding.tvSkip.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                dataStoreRepository.putBoolen(FIRST_TIME, false)
                findNavController().navigate(R.id.action_viewPagerFragment_to_signInFragment)
            }
        }
    }

    private fun setupUI() {

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = OnBoardingViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.apply {
            viewPager.adapter = adapter
        }
    }


}