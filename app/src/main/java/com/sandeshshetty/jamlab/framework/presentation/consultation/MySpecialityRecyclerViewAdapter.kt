package com.sandeshshetty.jamlab.framework.presentation.consultation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.databinding.FragmentSpecialityBinding


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MySpecialityRecyclerViewAdapter(
    private val listener: OnItemClickListener,
    private val values: ArrayList<Speciality>
) : RecyclerView.Adapter<MySpecialityRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentSpecialityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun submitList(list: List<Speciality>) {
        values.addAll(list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
//        holder.idView.text = item.id
        holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentSpecialityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = values[position]
                        listener.onItemClick(item)
                    }
                }
            }
        }

        val idView: ImageView = binding.specialityImageView
        val contentView: TextView = binding.specialityNameTextView

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    interface OnItemClickListener {
        fun onItemClick(speciality: Speciality)
    }

}