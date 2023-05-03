package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback

class InvestPageActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest_page)

        val spinner: Spinner = findViewById(R.id.rsDollar_dropDown);
//        val option = arrayListOf<String>("dollar", "rupees")
//       val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, option);
//        spinner.adapter = adapter;
//        spinner.setSelection(0);

        ArrayAdapter.createFromResource(this, R.array.invest_cashType, android.R.layout.simple_dropdown_item_1line).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter;
        }

        // Initialize the back button
        backButton = findViewById(R.id.investPage_bckBtn)

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

        // Set up the click listeners for Paypal Payment
        val InvestPay = findViewById<Button>(R.id.investPay_btn)
        InvestPay.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}