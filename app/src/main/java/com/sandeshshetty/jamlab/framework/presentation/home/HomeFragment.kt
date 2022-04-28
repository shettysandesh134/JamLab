package com.sandeshshetty.jamlab.framework.presentation.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.databinding.FragmentHomeBinding
import com.sandeshshetty.jamlab.utils.Constants.ACCESS_TOKEN
import com.sandeshshetty.jamlab.utils.Constants.FIRST_TIME
import com.sandeshshetty.jamlab.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {

    @Inject
    lateinit var dataStoreRepository: DataStoreRepository

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.homeToolbar.inflateMenu(R.menu.home_app_bar_menu)

        checkNavigation()

        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.consultationButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_specialityFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun checkNavigation() {

        lifecycleScope.launchWhenStarted {
            val firstTime = dataStoreRepository.getBoolean(FIRST_TIME)
            val token = dataStoreRepository.getString(ACCESS_TOKEN)
            if (firstTime == null || firstTime == true){
                findNavController().navigate(R.id.viewPagerFragment)
            }else if (token == null) {
                findNavController().navigate(R.id.signInFragment)
            }else {
                displayToast("Welcome")
            }
        }
    }

}