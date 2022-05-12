package com.sandeshshetty.jamlab.framework.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.ACCESS_TOKEN
import com.sandeshshetty.jamlab.business.data.preferences.util.FIRST_TIME
import com.sandeshshetty.jamlab.databinding.FragmentHomeBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import com.sandeshshetty.jamlab.utils.setUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var dataStoreRepository: DataStoreRepository

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var uiController: UIController? = null

//    private val permissionManager = PermissionManager.from(this)

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

//        permissionManager
//            .request(Permission.MandatoryFeatureForVideoCalling)
//            .rationale("Necessary for this to run")
//            .checkPermission { granted->
//                if (granted){
//                    displayToast("Success")
//                }else{
//                    displayToast("Still mission some permission")
//                }
//            }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        uiController = requireActivity().setUiController()
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
            if (firstTime == null || firstTime == true) {
                findNavController().navigate(R.id.viewPagerFragment)
            } else if (token == null) {
                findNavController().navigate(R.id.signInFragment)
            } else {
                displayToast("Welcome")
            }
        }
    }

}