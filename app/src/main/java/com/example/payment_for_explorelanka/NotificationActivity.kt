package com.example.payment_for_explorelanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class NotificationActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Initialize the back button
        backButton = findViewById(R.id.notificationBack_btn)

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


    }
}