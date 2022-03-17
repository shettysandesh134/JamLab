package com.sandeshshetty.jamlab.framework.presentation.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.databinding.FragmentUsersLocationBinding
import com.sandeshshetty.jamlab.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_users_location.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UsersLocationFragment : Fragment() {

    private val viewModel: UserLocationViewModel by viewModels()

    private  val TAG = "UsersLocationFragment"

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-33.8459712007337, 151.01793766197093)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private val adapter = PlacePredictionAdapter()

    private lateinit var binding: FragmentUsersLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_users_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersLocationBinding.bind(view)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        initSearchView(binding.searchAutoComplete)
        initRecyclerView(binding.recyclerView)

        binding.confirmBt.setOnClickListener {
            findNavController().navigate(R.id.action_usersLocation_to_dashBoardFragment)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchQuery.collect { event ->
                when (event) {
                    LocationSearchEvent.Empty -> {
                        Log.d(TAG, "onViewCreated: onEmpty")
                        viewModel.onSearchQueryChanged("")
                    }
                    is LocationSearchEvent.LocationSearchEventError -> view_animator.displayedChild = 0

                    is LocationSearchEvent.LocationSearchEventFound -> {
                        adapter.setPredictions(event.places)
                        view_animator.displayedChild = if (event.places.isEmpty()) 0 else 1
                    }
                }
            }
        }

    }

    private fun initSearchView(searchView: SearchView) {
        searchView.queryHint = "Search a view"
//        searchView.isIconifiedByDefault = false
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        searchView.onQueryTextChanged {
            viewModel.onSearchQueryChanged(it)
        }

    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@UsersLocationFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
        }
        adapter.onPlaceClickListener = {
            Log.d(TAG, "initRecyclerView: ${it.getFullText(null)}")
            viewModel.setPlaceClicked(LocationSearchEvent.Empty)

        }
    }

}
