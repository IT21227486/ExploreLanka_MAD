package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val payFirebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()



        // Initialize the back button
        backButton = findViewById(R.id.paymentMethodBack_btn)

        // Set up the click listener for the back button
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Set up the OnBackPressedCallback
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Call the finish() method to close the activity
                finish()
            }
        }

        // Add the callback to the OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, callback)


        // Set up the click listeners for card Payment
        val cardPay = findViewById<Button>(R.id.cardPayment_btn2)
        cardPay.setOnClickListener {
            val intent = Intent(this, CardPaymentActivity::class.java)
            startActivity(intent)
        }

        // Set up the click listeners for Paypal Payment
        val payPalPay = findViewById<Button>(R.id.paypalPay_btn)
        payPalPay.setOnClickListener {
            val intent = Intent(this, PaypalActivity::class.java)
            startActivity(intent)
        }

        // Set up the click listeners for cash Payment
        val cashPay = findViewById<Button>(R.id.cashPay_btn)
        cashPay.setOnClickListener {
            val intent = Intent(this, ShowPayInfo::class.java)
            startActivity(intent)
        }

    }
}