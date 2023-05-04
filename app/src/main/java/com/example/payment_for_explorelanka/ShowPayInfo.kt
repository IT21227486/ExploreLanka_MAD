package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShowPayInfo : AppCompatActivity() {


    private lateinit var btnInsertPaydata: Button
    private lateinit var btnFetchPaydata: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_pay_info)



        btnInsertPaydata = findViewById(R.id.addPayinfo)
        btnFetchPaydata = findViewById(R.id.fetchPayinfo)

        btnInsertPaydata.setOnClickListener {
            val intent = Intent(this, CrudPay::class.java)
            startActivity(intent)
        }

        btnFetchPaydata.setOnClickListener {
            val intent = Intent(this, FetchPaymentData::class.java)
            startActivity(intent)
        }



    }


}