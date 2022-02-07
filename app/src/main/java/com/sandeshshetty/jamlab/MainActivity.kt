package com.sandeshshetty.jamlab

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sandeshshetty.jamlab.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //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

    @Inject lateinit var hiltString: String
    @Inject lateinit var medicalService: MedicalService

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

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
}