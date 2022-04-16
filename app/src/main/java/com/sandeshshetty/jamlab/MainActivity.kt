package com.sandeshshetty.jamlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.business.domain.state.Response
import com.sandeshshetty.jamlab.business.domain.state.UIComponentType
import com.sandeshshetty.jamlab.databinding.ActivityMainBinding
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIController {

    @Inject lateinit var hiltString: String

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var uiController: UIController
    private var dialogInView: AlertDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Log.d("MainActivity", hiltString)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.splashFragment || destination.id == R.id.viewPagerFragment || destination.id == R.id.usersLocation){
//                binding.bottomNavigationView.apply {
//                    visibility = View.GONE
//                }
//            }else{
//                binding.bottomNavigationView.apply {
//                    visibility = View.VISIBLE
//                }
//            }

            binding.bottomNavigationView.apply {
                visibility = View.GONE
                if (destination.id == R.id.homeFragment) {
                    visibility = View.VISIBLE
                }
            }

        }

//        CoroutineScope(IO).launch {
//            medicalService.login()
//        }
    }

    override fun displayProgessBar(isDisplayed: Boolean) {
        main_progress_bar.apply {
            if (isDisplayed) {
                visibility = View.VISIBLE
            }else {
                visibility = View.GONE
            }
        }
    }

    override fun onResponseReceived(response: Response) {
        when(response.uiComponentType) {
            is UIComponentType.Toast -> {
                response.message?.let {
                    displayToast(message = it)
                }
            }

            is UIComponentType.Dialog -> {
                displayDialog(response)
            }
        }
    }

    private fun displayDialog(
        response: Response
    ){
        response.message?.let { message->
            dialogInView = when(response.messageType) {
                is MessageType.Error-> {
                    displayErrorDialog(message)
                }
                is MessageType.Success-> {
                    displaySuccessDialog(message)
                }
                is MessageType.Info-> {
                    displayInfoDialog(message)
                }
                else-> {
                    null
                }
            }
        }
    }

    private fun displaySuccessDialog(
        message: String?
    ): AlertDialog? {
        return MaterialAlertDialogBuilder(
            this,
            R.style.ThemeOverlay_MaterialComponents_Dialog_Alert
        )
            .setTitle(getString(R.string.dialog_success_title))
            .setMessage(message.toString())
            .setNegativeButton(getString(R.string.dialog_cancel_button)) { dialog, which->
                dialogInView = null
            }
            .setPositiveButton(getString(R.string.dialog_ok_button)){ dialog, which->

            }
            .show()

    }

    private fun displayErrorDialog(
        message: String?
    ): AlertDialog? {
        return MaterialAlertDialogBuilder(
            this,
            R.style.ThemeOverlay_MaterialComponents_Dialog_Alert
        )
            .setTitle(getString(R.string.dialog_error_title))
            .setMessage(message.toString())
            .setNegativeButton(getString(R.string.dialog_cancel_button)) { dialog, which->

            }
            .setPositiveButton(getString(R.string.dialog_ok_button)){ dialog, which->

            }
            .show()

    }

    private fun displayInfoDialog(
        message: String?
    ): AlertDialog? {
        return MaterialAlertDialogBuilder(
            this,
            R.style.ThemeOverlay_MaterialComponents_Dialog_Alert
        )
            .setTitle(getString(R.string.dialog_info_title))
            .setMessage(message.toString())
            .setNegativeButton(getString(R.string.dialog_cancel_button)) { dialog, which->
                dialogInView = null
            }
            .setPositiveButton(getString(R.string.dialog_ok_button)){ dialog, which->

            }
            .show()

    }

}