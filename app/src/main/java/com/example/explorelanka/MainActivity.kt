package com.example.explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase :DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val sigiriyaBtn = findViewById<Button>(R.id.sigiriya)
        sigiriyaBtn.setOnClickListener {
            val Intent = Intent (this, sigiriya::class.java)
            startActivity(Intent)
        }

        val ravanaCaveBtn = findViewById<Button>(R.id.ravana)
        ravanaCaveBtn.setOnClickListener {
            val Intent = Intent (this, RavanaCave::class.java)
            startActivity(Intent)
        }

        val yalaBtn = findViewById<Button>(R.id.yala)
        yalaBtn.setOnClickListener {
            val Intent = Intent (this, Yala::class.java)
            startActivity(Intent)
        }

        val sinharajaBtn = findViewById<Button>(R.id.sinharaja)
        sinharajaBtn.setOnClickListener {
            val Intent = Intent (this, Sinharaja::class.java)
            startActivity(Intent)
        }

        val pinnewalaBtn = findViewById<Button>(R.id.pinnewala)
        pinnewalaBtn.setOnClickListener {
            val Intent = Intent (this, Pinnewela::class.java)
            startActivity(Intent)
        }

        val worldEndBtn = findViewById<Button>(R.id.worldEnd)
        worldEndBtn.setOnClickListener {
            val Intent = Intent (this, worldEnd::class.java)
            startActivity(Intent)
        }

    }
}