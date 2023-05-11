package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
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
        // Initialize the pay button
        val investPay = findViewById<Button>(R.id.investPay_btn)

        // Initialize the all Textfields
        val investName = findViewById<EditText>(R.id.investOwnerName_text)
        val investEmail = findViewById<EditText>(R.id.investOwnerEmail_text)
        val investPhone = findViewById<EditText>(R.id.investOwnerPhone_text)
        val investAmount = findViewById<EditText>(R.id.investAmount)

        val editTexts = arrayOf(investName, investEmail, investPhone, investAmount)

        // Set up the TextWatchers for each EditText
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    // Check if all EditTexts are not empty
                    investPay.isEnabled = editTexts.none { it.text.isEmpty() }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }



        investPay.setOnClickListener {
            // Check if any EditText is empty
            if (editTexts.any { it.text.isEmpty() }) {
                // Show a toast message
                Toast.makeText(this@InvestPageActivity, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
            } else {

                // Get the amount from the investAmount EditText
                val amount = investAmount.text.toString()

                // Launch the PaymentActivity and pass the amount as an extra
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("amount", amount)
                startActivity(intent)
            }
        }




    }
}