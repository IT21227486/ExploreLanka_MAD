package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payment_for_explorelanka.PayAdapter.PayListAdapter
import com.example.payment_for_explorelanka.PayModel.PayListModel
import com.example.payment_for_explorelanka.databaseP.databasePHelper

class ShowPayInfo : AppCompatActivity() {

    lateinit var recycler_pay : RecyclerView
    lateinit var btn_add : Button
    var paylistAdapter : PayListAdapter ?= null
    var dbhanlder : databasePHelper ?= null
    var payList : List<PayListModel> = ArrayList<PayListModel>()
    var linearLayoutManager : LinearLayoutManager  ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_pay_info)

        recycler_pay = findViewById(R.id.show_payinfo)
        btn_add = findViewById(R.id.addPayinfo)

        dbhanlder = databasePHelper(this)
        fetchPayList()

        btn_add.setOnClickListener {
            val i = Intent(applicationContext, CrudPay::class.java)
            startActivity(i)
        }



    }

    private fun fetchPayList(){
        payList = dbhanlder!!.getAllInfo()
        paylistAdapter = PayListAdapter(payList, applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recycler_pay.layoutManager = linearLayoutManager
        recycler_pay.adapter = paylistAdapter
        paylistAdapter?.notifyDataSetChanged()


    }
}