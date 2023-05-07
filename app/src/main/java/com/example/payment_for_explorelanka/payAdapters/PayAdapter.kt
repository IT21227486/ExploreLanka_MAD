package com.example.payment_for_explorelanka.payAdapters

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.payment_for_explorelanka.PayModel.PaymentModel
import com.example.payment_for_explorelanka.R

class PayAdapter( private val payList: ArrayList<PaymentModel>): RecyclerView.Adapter<PayAdapter.ViewHolder>(){

     lateinit var pListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListner: onItemClickListner){
        pListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_payment_list_item, parent, false)
        return ViewHolder(itemView, pListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to ViewHolder
        val currentCustomer = payList[position]
        holder.tvCusName.text = currentCustomer.CustomerName

    }

    override fun getItemCount(): Int {
        return payList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner) : RecyclerView.ViewHolder(itemView) {
        // Define ViewHolder here

        val tvCusName : TextView = itemView.findViewById(R.id.tvCusName)

        init {
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }

    }
}