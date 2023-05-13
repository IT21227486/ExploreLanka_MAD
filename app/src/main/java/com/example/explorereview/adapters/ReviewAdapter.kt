package com.example.explorereview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explorereview.R
import com.example.explorereview.models.FeedBackModel

class ReviewAdapter(private val feedbackList: ArrayList<FeedBackModel>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private lateinit var rListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        rListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_review_list_item, parent, false)
        return ViewHolder(itemView, rListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = feedbackList[position]
        holder.tvEmpName.text = currentEmp.feedbackName
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val tvEmpName: TextView = itemView.findViewById(R.id.tvREmpName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    }



