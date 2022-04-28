package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.databinding.FragmentDoctorFilterBottomSheetBinding
import com.sandeshshetty.jamlab.utils.displayToast
import kotlinx.coroutines.flow.collectLatest


class DoctorFilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDoctorFilterBottomSheetBinding?= null
    private val binding get() = _binding!!
    private val doctorsViewModel: DoctorsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = doctorsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        binding.filterConfirmButton.setOnClickListener {
            doctorsViewModel.filterConfirmed()
            findNavController().popBackStack()
        }

    }

}