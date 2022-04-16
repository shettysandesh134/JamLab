package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.framework.presentation.consultation.placeholder.PlaceholderContent
import com.sandeshshetty.jamlab.utils.displayToast

/**
 * A fragment representing a list of Items.
 */
class DoctorsFragment : Fragment() {

    private var columnCount = 1
    val args: DoctorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctors_list, container, false)



        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = DoctorsRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.speciality?.let {
            displayToast(it.name)
        }
    }
}