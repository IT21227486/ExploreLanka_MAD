package com.example.explorelanka.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.view.View
//import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explorelanka.R
import com.example.explorelanka.models.SigiriyaModel

class TuoAdapter(private val tuoList: ArrayList<SigiriyaModel>) :
    RecyclerView.Adapter<TuoAdapter.ViewHolder>() {

    lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_tuo_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTuo = tuoList[position]
        holder.tvTuoName.text = currentTuo.nameSigiriya
    }

    override fun getItemCount(): Int {
        return tuoList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvTuoName : TextView = itemView.findViewById(R.id.tvTuoName)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}