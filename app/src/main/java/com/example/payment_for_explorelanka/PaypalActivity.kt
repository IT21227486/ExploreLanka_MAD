package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.OnBackPressedCallback

class PaypalActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);


        // Initialize the back button
        backButton = findViewById(R.id.paypalBack_btn)

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

        val paypalPay = findViewById<Button>(R.id.paypalProceed_btn)
        val textView: TextView = findViewById(R.id.paypalOfficial_text);
        val nameOnOwner = findViewById<EditText>(R.id.nameOnPaypal_text)
        val emailPaypal = findViewById<EditText>(R.id.emailOnPaypal_text)
        val paypalPhone = findViewById<EditText>(R.id.paypalOwnerPhone_text)
        val paypalTotal = findViewById<EditText>(R.id.totalInPaypalPay_value)

        // Initialize the array of EditTexts
        val editTexts = arrayOf(nameOnOwner, emailPaypal, paypalPhone, paypalTotal)

        // Disable the button by default
        paypalPay.isEnabled = false

        for (editText in editTexts){
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    // Check if any EditText is empty
                    if (editTexts.any { it.text.isEmpty() }) {
                        // Disable the button if any EditText is empty
                        paypalPay.isEnabled = false
                        // Show a toast message
                        Toast.makeText(this@PaypalActivity, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
                    } else {
                        // Enable the button if all EditTexts are not empty
                        paypalPay.isEnabled = true
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        val text = "Use below information in your paypal transfer.\\nEmail: explorelanka@gmail.com\\nuserID: Eplore Lanka"



        paypalPay.setOnClickListener {
            val intent = Intent(this, BookingCompletedActivity::class.java)
            startActivity(intent)
        }





    }
}