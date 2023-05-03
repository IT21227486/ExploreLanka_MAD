


package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class CashPaymentActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_payment)



        // Initialize the back button
        backButton = findViewById(R.id.cashPaymentBack_btn)

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

       val totalInCashPayValue = findViewById<EditText>(R.id.totalInCashPay_value)
        val cashOwnerEmail = findViewById<EditText>(R.id.cashOwnerEmail_text)
        val cashPayDescriptionText = findViewById<EditText>(R.id.cashPay_descriptionText)
        val cashOwnerName = findViewById<EditText>(R.id.cashOwnerName_text)
        val cashOwnerPhone = findViewById<EditText>(R.id.cashOwnerPhone_text)


        val editTexts = arrayOf(totalInCashPayValue, cashOwnerEmail, cashPayDescriptionText, cashOwnerName, )


//        // Set up the TextWatchers for each EditText
//        for (editText in editTexts) {
//            editText.addTextChangedListener(object : TextWatcher {
//                override fun afterTextChanged(s: Editable) {
//                    // Check if all EditTexts are not empty
//                    cardPay.isEnabled = editTexts.none { it.text.isEmpty() }
//                }
//
//                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            })
//        }


        val cardPay = findViewById<Button>(R.id.cashPayConfirm_btn)
        cardPay.setOnClickListener {
            val intent = Intent(this, BookingCompletedActivity::class.java)
            startActivity(intent)
        }
    }
}