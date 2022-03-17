package com.sandeshshetty.jamlab.framework.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.ACCESS_TOKEN
import com.sandeshshetty.jamlab.business.data.preferences.util.NAME
import com.sandeshshetty.jamlab.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.doctorConsulting.setOnClickListener {
            val value: String = binding.edtxt.text.toString()

            value.let {
                homeViewModel.changeName(it)
            }

        }

        binding.getDataBtn.setOnClickListener {
            val job = homeViewModel.getData(ACCESS_TOKEN)
            binding.doctorConsulting.text = job.toString()
        }
    }
}