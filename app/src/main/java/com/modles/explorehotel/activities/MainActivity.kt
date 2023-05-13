package com.modles.explorehotel.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.modles.explorehotel.R

class MainActivity : AppCompatActivity() {
    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnInsertData = findViewById(R.id.Hotel_btnInsertData)
        btnFetchData = findViewById(R.id.Hotel_btnFetchData)

        btnInsertData.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }

//        btnFetchData.setOnClickListener {
//            val intent = Intent(this, FetchingActivity::class.java)
//            startActivity(intent)
//        }

    }
}
