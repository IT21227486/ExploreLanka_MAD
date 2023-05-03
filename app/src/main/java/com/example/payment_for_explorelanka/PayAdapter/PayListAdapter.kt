package com.example.payment_for_explorelanka.PayAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.payment_for_explorelanka.CrudPay
import com.example.payment_for_explorelanka.PayModel.PayListModel
import com.example.payment_for_explorelanka.R

class PayListAdapter(payList: List<PayListModel>, internal var context: Context)
    :RecyclerView.Adapter<PayListAdapter.PayViewHolder>()
{
    internal var payList: List<PayListModel> = ArrayList()


    inner class PayViewHolder(view: View):RecyclerView.ViewHolder(view){
        var name : TextView = view.findViewById(R.id.editCustName)
        var phoneNo : TextView = view.findViewById(R.id.editCusNo)
        var payType : TextView = view.findViewById(R.id.editPayType)
        var bookType : TextView = view.findViewById(R.id.editBookType)
        var amount : TextView = view.findViewById(R.id.editAmount)
        var btn_edit: Button = view.findViewById<Button>(R.id.editPayBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_pay_list, parent, false)
        return PayViewHolder(view)

    }

    override fun onBindViewHolder(holder: PayViewHolder, position: Int) {
        val pays = payList[position]
        holder.name.text = pays.CustomerName
        holder.phoneNo.text = pays.CustomerNum
        holder.payType.text = pays.payType
        holder.bookType.text = pays.bookType
        holder.amount.text = pays.amount

        holder.btn_edit.setOnClickListener {
            val i = Intent(context, CrudPay::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", pays.id)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {

        return payList.size

    }


}