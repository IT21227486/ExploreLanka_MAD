package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class BookingCompletedActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var crudButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_completed)


        // Initialize the back button
        backButton = findViewById(R.id.bookingSuccessBack_btn2)

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

        crudButton = findViewById(R.id.menu_btnComplete)

        crudButton.setOnClickListener{
            val intent = Intent(this, ShowPayInfo::class.java)
            startActivity(intent)
        }


    }
}