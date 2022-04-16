package com.sandeshshetty.jamlab.framework.presentation.authenticate.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.sandeshshetty.jamlab.MainActivity
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.databinding.FragmentSignInBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val signinViewModel: SigninViewModel by viewModels()

    private lateinit var uiController: UIController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setUIController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setUpUI()
       subscribeObservers()
    }

    private fun setUpUI() {

        // SignInClick Listener
        binding.btSignin.setOnClickListener {
//            findNavController().navigate(R.id.action_signInFragment_to_usersLocation)
            val email = binding.edtextUsername.text
            val password = binding.edtextPassword.text
//            printLogD("SiginINFragment",email.toString().isEmailVerified().toString())
            signinViewModel.setStateEvent(
                AuthenticateStateEvent.LoginUserEvent(
                    email.toString(),
                    password = password.toString()
                )
            )
        }

        // RegisterClick Listener
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }

    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signinViewModel.sharedFlow.collect { response ->
                    when (response.messageType) {
                        is MessageType.Success -> {
                            findNavController().navigate(R.id.action_signInFragment_to_usersLocation)
                        }

                        is MessageType.Error -> {
                            uiController.onResponseReceived(response)
//                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        signinViewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner, Observer{
            uiController.displayProgessBar(it)
        })


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