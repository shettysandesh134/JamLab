package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.databinding.FragmentDoctorsListBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import com.sandeshshetty.jamlab.utils.setUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class DoctorsFragment : Fragment(), DoctorsRecyclerViewAdapter.onDoctorItemClickListener {

    val args: DoctorsFragmentArgs by navArgs()

    private var _binding: FragmentDoctorsListBinding? = null
    private val binding get() = _binding!!
    private val doctorsViewModel: DoctorsViewModel by activityViewModels()
    private val doctorsAdapter = DoctorsRecyclerViewAdapter(this)

    private var uiController: UIController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoctorsListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.speciality?.let {
            doctorsViewModel.setStateEvent(DoctorsStateEvent.GetDoctorsListEvent(it))
        }

        binding.doctorsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsAdapter
        }

        collectFlows()
        collectEvents()

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.doctor_list_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                doctorsViewModel.setStateEvent(DoctorsStateEvent.FilterButtonClickedEvent)
            }
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        uiController = requireActivity().setUiController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                doctorsViewModel.doctorStateFlow.collect { doctorsState ->
                    doctorsState.filteredDoctors?.let { doctors ->
                        doctorsAdapter.submitList(doctors)
                    }
                }
            }
        }

        doctorsViewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner, Observer {
            uiController?.displayProgessBar(it)
        })
    }

    private fun collectEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                doctorsViewModel.doctorsListEvent.collect {
                    when (it) {
                        is DoctorsStateEvent.FilterButtonClickedEvent -> {
                            findNavController().navigate(R.id.action_doctorsFragment_to_doctorFilterBottomSheetFragment)
                        }

                        is DoctorsStateEvent.DoctorItemClickedEvent-> {
                            val action = DoctorsFragmentDirections.actionDoctorsFragmentToDoctorDetailFragment(it.doctor)
                            findNavController().navigate(action)
                        }
                    }
                }

            }
        }
    }

    override fun onItemClick(doctor: Doctor) {
        doctorsViewModel.setStateEvent(DoctorsStateEvent.DoctorItemClickedEvent(doctor))
    }
}
