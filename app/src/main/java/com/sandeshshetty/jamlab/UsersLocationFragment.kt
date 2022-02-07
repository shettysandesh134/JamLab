package com.sandeshshetty.jamlab

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.*
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.sandeshshetty.jamlab.databinding.FragmentUsersLocationBinding
import com.sandeshshetty.jamlab.databinding.UserLocationViewBinding
import kotlinx.android.synthetic.main.fragment_users_location.*


class UsersLocationFragment : Fragment() {

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

    val bias: LocationBias = RectangularBounds.newInstance(
        LatLng(22.458744, 88.208162),  // SW lat, lng
        LatLng(22.730671, 88.524896) // NE lat, lng
    )
    private lateinit var placesClient: PlacesClient

    private var sessionToken: AutocompleteSessionToken? = null

    private val handler = Handler()
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

//        val countries = arrayListOf<String>(
//            "Belgium",
//            "France",
//            "Italy",
//            "Germany"
//        )
//        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countries)
//        searchAutoComplete.setAdapter(adapter)



        val apiKey = BuildConfig.PLACES_API_KEY
        Places.initialize(context, apiKey)
        placesClient = Places.createClient(context)


            initSearchView(binding.searchAutoComplete)
            initRecyclerView(binding.recyclerView)

            binding.confirmBt.setOnClickListener {
                findNavController().navigate(R.id.action_usersLocation_to_dashBoardFragment)
            }

    }

    private fun initSearchView(searchView: SearchView) {
        searchView.queryHint = "Search a view"
        searchView.isIconifiedByDefault = false
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.requestFocusFromTouch()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                progressBar.isIndeterminate = true

                // Cancel any previous place prediction requests
                handler.removeCallbacksAndMessages(null)

                // Start a new place prediction request in 300 ms
                handler.postDelayed({ getPlacePredictions(newText) }, 300)
                return true
            }
        })
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
//        adapter.onPlaceClickListener = { geocodePlaceAndDisplay(it) }
    }

    /**
     * This method demonstrates the programmatic approach to getting place predictions. The
     * parameters in this request are currently biased to Kolkata, India.
     *
     * @param query the plus code query string (e.g. "GCG2+3M K")
     */
    private fun getPlacePredictions(query: String) {
        // The value of 'bias' biases prediction results to the rectangular region provided
        // (currently Kolkata). Modify these values to get results for another area. Make sure to
        // pass in the appropriate value/s for .setCountries() in the
        // FindAutocompletePredictionsRequest.Builder object as well.
//        val bias: LocationBias = RectangularBounds.newInstance(
//            LatLng(22.458744, 88.208162),  // SW lat, lng
//            LatLng(22.730671, 88.524896) // NE lat, lng
//        )

        // Create a new programmatic Place Autocomplete request in Places SDK for Android
        val newRequest = FindAutocompletePredictionsRequest
            .builder()
            .setSessionToken(sessionToken)
            .setLocationBias(bias)
            .setTypeFilter(TypeFilter.ADDRESS)
            .setQuery(query)
            .setCountry("AU")
            .build()

        // Perform autocomplete predictions request
        placesClient.findAutocompletePredictions(newRequest).addOnSuccessListener { response ->
            val predictions = response.autocompletePredictions
            adapter.setPredictions(predictions)
//            progressBar.isIndeterminate = false
            binding.viewAnimator.displayedChild = if (predictions.isEmpty()) 0 else 1
        }.addOnFailureListener { exception: Exception? ->
//            progressBar.isIndeterminate = false
            if (exception is ApiException) {
                Log.e("TAG", "Place not found: " + exception.statusCode)

            }
        }
    }

//        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
//        // and once again when the user makes a selection (for example when calling fetchPlace()).
//        val placesClient = Places.createClient(activity)
//        val token = AutocompleteSessionToken.newInstance()
//
//        // Create a RectangularBounds object.
//        val bounds = RectangularBounds.newInstance(
//            LatLng(-33.880490, 151.184363),
//            LatLng(-33.858754, 151.229596)
//        )
//        // Use the builder to create a FindAutocompletePredictionsRequest.
//        val request =
//            FindAutocompletePredictionsRequest.builder()
//                // Call either setLocationBias() OR setLocationRestriction().
//                .setLocationBias(bounds)
//                //.setLocationRestriction(bounds)
//                .setOrigin(LatLng(-33.8749937, 151.2041382))
//                .setCountries("AU", "NZ")
//                .setTypeFilter(TypeFilter.ADDRESS)
//                .setSessionToken(token)
//                .setQuery("114 SH")
//                .build()
//        placesClient.findAutocompletePredictions(request)
//            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
//                for (prediction in response.autocompletePredictions) {
////                    Log.i("TAG", prediction.placeId)
//                    Log.i("TAG", prediction.getPrimaryText(null).toString())
//                }
//            }.addOnFailureListener { exception: Exception? ->
//                if (exception is ApiException) {
//                    Log.e("TAG", "Place not found: " + exception.statusCode)
//                }
//            }


//        searchAutoComplete.setOnClickListener {
//            val AUTOCOMPLETE_REQUEST_CODE = 1
//
//            // Set the fields to specify which types of place data to
//            // return after the user has made a selection.
//            val fields = listOf(Place.Field.ID, Place.Field.NAME)
//
//            // Start the autocomplete intent.
//            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
//                .build(view.context)
//
//            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
//        }



    }