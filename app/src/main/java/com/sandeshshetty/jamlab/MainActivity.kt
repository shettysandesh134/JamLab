package com.sandeshshetty.jamlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sandeshshetty.jamlab.business.domain.state.Response
import com.sandeshshetty.jamlab.business.domain.state.UIComponentType
import com.sandeshshetty.jamlab.databinding.ActivityMainBinding
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIController {

    @Inject lateinit var hiltString: String

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var uiController: UIController

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
            if (destination.id == R.id.splashFragment || destination.id == R.id.viewPagerFragment || destination.id == R.id.usersLocation){
                binding.bottomNavigationView.apply {
                    visibility = View.GONE
                }
            }else{
                binding.bottomNavigationView.apply {
                    visibility = View.VISIBLE
                }
            }
        }

//        CoroutineScope(IO).launch {
//            medicalService.login()
//        }
    }

    override fun onResponseReceived(response: Response) {
        when(response.uiComponentType) {
            is UIComponentType.Toast -> {
                response.message?.let {
                    displayToast(message = it)
                }
            }
        }
    }
}