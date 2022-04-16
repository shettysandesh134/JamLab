 package com.sandeshshetty.jamlab.framework.presentation.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.MainActivity
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.databinding.FragmentProfileBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

 @AndroidEntryPoint
 class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
     private val binding get() = _binding!!
     private val profileViewModel: ProfileViewModel by viewModels()

     private lateinit var uiController: UIController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

     override fun onAttach(context: Context) {
         super.onAttach(context)
         setUIController()
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         val fname = binding.editTextTextPersonName.text
         binding.editUserProfileButton.setOnClickListener {
             displayToast("${fname.toString()} here")
             val user = User(id = 1, lid = "1", fname = fname.toString(), lname = "testinglname", channel = "p1_1234")
            profileViewModel.setStateEvent(ProfileStateEvent.EditUserProfileEvent(user = user))
         }


        subscribeObservers()
     }

     private fun subscribeObservers() {
         lifecycleScope.launch {
             repeatOnLifecycle(Lifecycle.State.STARTED) {
                 profileViewModel.sharedFlow.collect { response ->
                     when (response.messageType) {
                         is MessageType.Success -> {
//                             findNavController().navigate(R.id.action_signInFragment_to_usersLocation)
                         }

                         is MessageType.Error -> {
                             uiController.onResponseReceived(response)
//                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                         }
                     }
                 }
             }
         }
     }

     private fun setUIController() {
         activity?.let {
             if (it is MainActivity) {
                 try {
                     uiController = requireActivity() as UIController
                 } catch (e: ClassCastException) {
                     e.printStackTrace()
                 }

             }
         }
     }

}