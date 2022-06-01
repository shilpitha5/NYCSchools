package com.example.a20220601_shilpithapai_nycschools.schoollist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220601_shilpithapai_nycschools.databinding.ItemSchoolBinding
import com.example.a20220601_shilpithapai_nycschools.models.School

class SchoolListAdapter(private val showMoreDetails: (School) -> Unit) :
    PagingDataAdapter<School, SchoolListAdapter.DataHolder>(DiffCallBack) {

    inner class DataHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(school: School) {
            binding.schoolName.text = school.school_name
            "${school.city}, ${school.state_code} - ${school.zip}".also { binding.schoolCity.text = it }
            binding.showDetails.setOnClickListener {
                showMoreDetails(school)
            }
        }
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(
            ItemSchoolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

object DiffCallBack : DiffUtil.ItemCallback<School>() {
    override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
        return oldItem.dbn == newItem.dbn
    }

    override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
        return oldItem == newItem
    }
}
