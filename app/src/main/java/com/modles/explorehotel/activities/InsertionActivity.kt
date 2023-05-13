package com.modles.explorehotel.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.modles.explorehotel.R

class InsertionActivity : AppCompatActivity() {

    private lateinit var etHotelClientName: EditText
    private lateinit var etHotelClientPhone: EditText
    private lateinit var etHotelClientAmount: EditText
    private lateinit var HotelbtnSaveData: Button

   private lateinit var dbHRef: DatabaseReference

         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             setContentView(R.layout.activity_insertion)

             etHotelClientName = findViewById(R.id.etHotel_clientName)
             etHotelClientPhone = findViewById(R.id.etHotel_clientPhoneNo)
             etHotelClientAmount = findViewById(R.id.etHotel_clientAmount)
             HotelbtnSaveData = findViewById(R.id.Hotel_clientbtnSave)

             dbHRef = FirebaseDatabase.getInstance().getReference("Hotels")
             HotelbtnSaveData.setOnClickListener {
                 saveHotelsData()
             }
         }
    private fun saveHotelsData() {

        //getting values
        val ClientName = etHotelClientName.text.toString()
        val ClientPhone = etHotelClientPhone.text.toString()
        val ClientAmount = etHotelClientPhone.text.toString()

        if (ClientName.isEmpty()) {
            etHotelClientName.error = "Please enter name"
        }
        if (ClientPhone.isEmpty()) {
            etHotelClientPhone.error = "Please enter phone"
        }
        if (ClientAmount.isEmpty()) {
            etHotelClientAmount.error = "Please enter Amount of members"
        }

        val Hotel_ClientId = dbHRef.push().key!!

        val Hotels = HotelsModel(Hotel_ClientId,ClientName,ClientPhone,ClientAmount)

        dbHRef.child(Hotel_ClientId).setValue(Hotels)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etHotelClientName.text.clear()
                etHotelClientPhone.text.clear()
                etHotelClientAmount.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}

