package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class MedicineMarketActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_market)

        // Initialize the back button
        backButton = findViewById(R.id.medicine_backBtn)

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


        val medicineProceed = findViewById<Button>(R.id.medicinePage_proceedBtn)
        medicineProceed.setOnClickListener {
            val intent = Intent(this, InvestPageActivity::class.java)
            startActivity(intent)
        }

        val cancelInvest = findViewById<Button>(R.id.medicinePage_cancelBtn)
        cancelInvest.setOnClickListener {
            val intent = Intent(this, MarketPlace_activity::class.java)
            startActivity(intent)
        }

    }
}