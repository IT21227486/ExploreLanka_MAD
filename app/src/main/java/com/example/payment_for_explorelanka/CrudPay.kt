package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.payment_for_explorelanka.PayModel.PayListModel
import com.example.payment_for_explorelanka.databaseP.databasePHelper

class CrudPay : AppCompatActivity() {

    lateinit var btn_save : Button
    lateinit var btn_del : Button
    lateinit var cusName :EditText
    lateinit var cusNo :EditText
    lateinit var payType :EditText
    lateinit var bookType :EditText
    lateinit var amount :EditText

    var dbhandler : databasePHelper ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_pay)

        btn_save = findViewById(R.id.save_button)
        btn_del = findViewById(R.id.delete_button)
        cusName = findViewById(R.id.customerName)
        cusNo = findViewById(R.id.CustomerNumber)
        payType = findViewById(R.id.payType)
        bookType = findViewById(R.id.bookType)
        amount = findViewById(R.id.amount)
        var isEditMode: Boolean = false
        dbhandler = databasePHelper(this)

        if(intent != null && intent.getStringExtra("Mode")=="E"){
            //Update data
            isEditMode = true
            btn_save.text = "Update Data"
            btn_del.visibility = View.VISIBLE
            val pays : PayListModel = dbhandler!!.getPayinfo(intent.getIntExtra("id", 0))
            cusName.setText(pays.CustomerName)
            cusNo.setText(pays.CustomerNum)
            payType.setText(pays.payType)
            bookType.setText(pays.bookType)
            amount.setText(pays.amount)

        }else{

            isEditMode = false
            btn_save.text = "save Data"
            btn_del.visibility = View.GONE

        }

        btn_save.setOnClickListener {

            var success :Boolean = false
            var pays : PayListModel = PayListModel()

            if(isEditMode){
                //update data
                intent?.getIntExtra("Id", 0)?.let { pays.id = it }
                pays.CustomerName = cusName.text.toString()
                pays.CustomerNum = cusNo.text.toString()
                pays.payType = payType.text.toString()
                pays.bookType = bookType.text.toString()
                pays.amount = amount.text.toString()

                success = dbhandler?.updatePayInfo(pays) as Boolean


            }else{
                //insert data
                pays.CustomerName = cusName.text.toString()
                pays.CustomerNum = cusNo.text.toString()
                pays.payType = payType.text.toString()
                pays.bookType = bookType.text.toString()
                pays.amount = amount.text.toString()


                success = dbhandler?.addPayinfo(pays)as Boolean
            }

            if(success){
                var i = Intent(applicationContext, ShowPayInfo::class.java)
                startActivity(i)
                finish()
            }else{
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }


        btn_del.setOnClickListener {
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click Yes if you Want to the pay").setPositiveButton("Yes",{dialog, i ->
                val success = dbhandler?.deletePayInfo(intent.getIntExtra("Id",0)) as Boolean
                if(success){
                    finish()
                    dialog.dismiss()
                }
            })
                .setNegativeButton("No",{dialog, i->
                    dialog.dismiss()
                })
            dialog.show()

        }


    }
}