package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.databinding.FragmentDoctorsBinding
import kotlinx.android.synthetic.main.fragment_doctors.view.*


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class DoctorsRecyclerViewAdapter(
    private val listener: onDoctorItemClickListener
) : RecyclerView.Adapter<DoctorsRecyclerViewAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Doctor>() {
        override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDoctorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList.get(position)
        holder.doctorName.text = item.fname
        holder.doctorSpeciality.text = item.specialities?.get(0)?.name.toString()
        holder.doctorLocation.text = item.gender
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(doctors: List<Doctor>) {
        differ.submitList(doctors)
    }

    inner class ViewHolder(binding: FragmentDoctorsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList.get(position)
                        listener.onItemClick(item)
                    }
                }
            }
        }

        val doctorName: TextView = binding.doctorNameTextView
        val doctorSpeciality: TextView = binding.doctorSpecialityTextView
        val doctorLocation: TextView = binding.doctorLocationTextView

        override fun toString(): String {
            return super.toString() + " '" + doctorSpeciality.text + "'"
        }
    }

    interface onDoctorItemClickListener {
        fun onItemClick(doctor: Doctor)
    }

}