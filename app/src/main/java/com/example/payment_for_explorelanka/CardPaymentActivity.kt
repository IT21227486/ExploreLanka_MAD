package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class CardPaymentActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_payment)

        // Initialize the back button
        backButton = findViewById(R.id.cardPay_backBtn)

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

        val cardPay = findViewById<Button>(R.id.finalPay_btn)
        val nameOnCartText = findViewById<EditText>(R.id.nameOnCart_text)
        val cardNumberText = findViewById<EditText>(R.id.cardNumber_text)
        val expiryText = findViewById<EditText>(R.id.cardexpDate_text)
        val cvvText = findViewById<EditText>(R.id.cardCvvNum_text)
        val phoneNo = findViewById<EditText>(R.id.cardOwnerPhone_text)

        // Initialize the array of EditTexts
        val editTexts = arrayOf(nameOnCartText, cardNumberText, expiryText, cvvText, phoneNo)

//        // Disable the button by default
//        cardPay.isEnabled = false

        // Set up the TextWatchers for each EditText
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    // Check if all EditTexts are not empty
                    cardPay.isEnabled = editTexts.none { it.text.isEmpty() }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        cardPay.setOnClickListener {
            // Check if any EditText is empty
            if (editTexts.any { it.text.isEmpty() }) {
                // Show a toast message
                Toast.makeText(this@CardPaymentActivity, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
            } else {
                // Launch the BookingCompletedActivity
                val intent = Intent(this, BookingCompletedActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
