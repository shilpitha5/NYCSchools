package com.example.a20220601_shilpithapai_nycschools.schoollist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220601_shilpithapai_nycschools.databinding.ItemSchoolBinding
import com.example.a20220601_shilpithapai_nycschools.models.School

class SchoolListAdapter(private val clickFunction: (String) -> Unit) :
    PagingDataAdapter<School, SchoolListAdapter.Dataholder>(DiffCallBack) {
    inner class Dataholder(private val itembinding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(itembinding.root) {

        fun bind(school: School) {
            itembinding.schoolName.text = school.school_name
            itembinding.schoolCity.text = school.city
            itembinding.viewMore.setOnClickListener {
                clickFunction(school.dbn)
            }
        }
    }

    override fun onBindViewHolder(holder: Dataholder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dataholder {
        return Dataholder(
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