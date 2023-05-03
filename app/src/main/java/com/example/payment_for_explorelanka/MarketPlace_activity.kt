package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class MarketPlace_activity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_place)

        // Initialize the back button
        backButton = findViewById(R.id.marketPlaceBck_brn)

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


        // Find your textile image button by its ID
        val textileImageButton = findViewById<ImageButton>(R.id.txtileMarket_imgBtn)

        // Set an onClickListener for the image button
        textileImageButton.setOnClickListener {
            // Create an Intent that specifies the new activity to navigate to
            val intent = Intent(this, TextileMarketActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }


        // Find your seafoodImageButton image button by its ID
        val seafoodImageButton = findViewById<ImageButton>(R.id.seaMarket_imgBtn)

        // Set an onClickListener for the image button
        seafoodImageButton.setOnClickListener {
            // Create an Intent that specifies the new activity to navigate to
            val intent = Intent(this, SeaFoodMarketActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }


        // Find your seafoodImageButton image button by its ID
        val spicesImageButton = findViewById<ImageButton>(R.id.spicesMarket_imgBtn)

        // Set an onClickListener for the image button
        spicesImageButton.setOnClickListener {
            // Create an Intent that specifies the new activity to navigate to
            val intent = Intent(this, SpicesMarketActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }


        // Find your medicineImageButton image button by its ID
        val medicineImageButton = findViewById<ImageButton>(R.id.medicineMarket_imgBtn)

        // Set an onClickListener for the image button
        medicineImageButton.setOnClickListener {
            // Create an Intent that specifies the new activity to navigate to
            val intent = Intent(this, MedicineMarketActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }


    }
}