package com.sandeshshetty.jamlab.framework.presentation.signin

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
import com.sandeshshetty.jamlab.MainActivity
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.business.domain.state.UIComponentType
import com.sandeshshetty.jamlab.databinding.FragmentSignInBinding
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInStateEvent
import com.sandeshshetty.jamlab.utils.printLogD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.ClassCastException

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val signinViewModel: SigninViewModel by viewModels()

    lateinit var uiController: UIController

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

        binding.btSignin.setOnClickListener {
//            findNavController().navigate(R.id.action_signInFragment_to_usersLocation)
            val email = binding.edtextUsername.text
            val password = binding.edtextPassword.text
            signinViewModel.setStateEvent(SignInStateEvent.LoginUserEvent(email.toString(), password = password.toString()))
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signinViewModel.signInSharedFlow.collect { response ->
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
    }

    fun setUIController() {
        activity?.let {
            if (it is MainActivity) {
                try {
                    uiController = requireActivity() as UIController
                }catch (e: ClassCastException) {
                    e.printStackTrace()
                }

            }
        }
    }

}