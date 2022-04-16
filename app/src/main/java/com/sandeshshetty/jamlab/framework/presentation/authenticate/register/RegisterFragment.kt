package com.sandeshshetty.jamlab.framework.presentation.authenticate.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sandeshshetty.jamlab.MainActivity
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.databinding.RegisterFragmentBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateStateEvent
import com.sandeshshetty.jamlab.utils.displayToast
import com.sandeshshetty.jamlab.utils.printLogD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var uiController: UIController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        collectFlow()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setUIController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI() {

        binding.buttonRegister.setOnClickListener {

            val name = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()

            val request = RegisterRequest(name,email, password, confirmPassword,"patient")
            viewModel.setStateEvent(AuthenticateStateEvent.RegisterEvent(request))
        }
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sharedFlow.collect { response ->
                    when (response.messageType) {
                        is MessageType.Success -> {
//                            printLogD("RegisterFragment", response.message.toString())
                            findNavController().navigate(R.id.action_registerFragment_to_usersLocation)
                        }

                        is MessageType.Error -> {
//                            val gson = Gson()
//                            val value = gson.fromJson(response.message.toString(), RegisterErrorResponse::class.java)
//                            val message = value.message
//                            requireActivity().displayToast(message)
                            uiController.onResponseReceived(response)
//                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
//                            printLogD("RegisterFragment", response.message.toString())
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