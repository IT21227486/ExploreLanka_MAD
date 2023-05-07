package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.payment_for_explorelanka.PayModel.PaymentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrudPay : AppCompatActivity() {

    private lateinit var btn_save : Button
    private lateinit var cusName :EditText
    private lateinit var cusNo :EditText
    private lateinit var payType :EditText
    private lateinit var bookType :EditText
    private lateinit var amount :EditText

    private lateinit var payDbref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_pay)

        btn_save = findViewById(R.id.save_button)
//        btn_del = findViewById(R.id.delete_button)

        cusName = findViewById(R.id.customerName)
        cusNo = findViewById(R.id.CustomerNumber)
        payType = findViewById(R.id.payType)
        bookType = findViewById(R.id.bookType)
        amount = findViewById(R.id.amount)

        payDbref = FirebaseDatabase.getInstance().getReference("Payments")

        btn_save.setOnClickListener {
            savePaymentData()
            val intent = Intent(this, FetchPaymentData::class.java)
            startActivity(intent)
        }


    }

    private fun savePaymentData(){
        // getting payment values

        Log.d("savePaymentData", "Button clicked") // Debug statement

        val customerName = cusName.text.toString()
        val customerNumber = cusNo.text.toString()
        val paymentType = payType.text.toString()
        val bookingType = bookType.text.toString()
        val payAmount = amount.text.toString()

        if(customerName.isEmpty()){
            cusName.error =  "Please enter customer name"
        }
        if(customerNumber.isEmpty()){
            cusNo.error =  "Please enter customer Number"
        }
        if(paymentType.isEmpty()){
            payType.error =  "Please enter Payment Type"
        }
        if(bookingType.isEmpty()){
            bookType.error =  "Please enter Booking Type"
        }
        if(payAmount.isEmpty()){
            amount.error =  "Please enter Oayment AMount"
        }

        val cusId = payDbref.push().key!!

        val payment = PaymentModel(cusId, customerName, customerNumber, paymentType, bookingType, payAmount)

        payDbref.child(cusId).setValue(payment)
            .addOnCompleteListener {
                Toast.makeText(this, "payment data inserted succesfully", Toast.LENGTH_LONG).show()

//                cusName.text.clear()
//                cusNo.text.clear()
//                payType.text.clear()
//                bookType.text.clear()
//                amount.text.clear()

                Log.d("savePaymentData", "Payment data inserted successfully") // Debug statement

            }.addOnFailureListener {  err->

                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                Log.e("savePaymentData", "Error inserting payment data: ${err.message}") // Error log

            }
    }

}