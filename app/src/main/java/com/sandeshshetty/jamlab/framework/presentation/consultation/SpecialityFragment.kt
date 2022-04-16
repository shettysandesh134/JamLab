package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.databinding.FragmentSpecialityListBinding
import com.sandeshshetty.jamlab.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class SpecialityFragment : Fragment(), MySpecialityRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 2

    private var _binding: FragmentSpecialityListBinding? = null
    private val binding get() = _binding!!
    private val specialityViewModel: SpecialityViewModel by viewModels()

    private val specialities: ArrayList<Speciality> = ArrayList<Speciality>()
    val specialityAdapter = MySpecialityRecyclerViewAdapter(this, specialities)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSpecialityListBinding.inflate(inflater, container, false)

//        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = MySpecialityRecyclerViewAdapter(this, PlaceholderContent.ITEMS, )
//            }
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            specialityRecyclerView.apply {
                adapter = specialityAdapter
                layoutManager = GridLayoutManager(requireContext(), columnCount)
                setHasFixedSize(true)
            }
        }

        subsscribeFlow()

    }

    private fun subsscribeFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                specialityViewModel.specialityViewState.collect{ state->
                    state.specialities?.let {
                        specialityAdapter.submitList(it)
                        specialityAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                specialityViewModel.sharedFlow.collect { response ->
                    when (response.messageType) {
                        is MessageType.Success -> {

                        }

                        is MessageType.Error -> {
                            displayToast(response.message.toString())
//                            uiController.onResponseReceived(response)
//                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                specialityViewModel.specialitySharedFlow.collect { stateEvent->
                    when (stateEvent) {
                        is SpecialityStateEvent.OnSpecialityClick->{
                            val action = SpecialityFragmentDirections.actionSpecialityFragmentToDoctorsFragment(stateEvent.speciality)
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }

    }


    override fun onItemClick(speciality: Speciality) {
        displayToast(speciality.name)
        specialityViewModel.setStateEvent(SpecialityStateEvent.OnSpecialityClick(speciality = speciality))
    }
}