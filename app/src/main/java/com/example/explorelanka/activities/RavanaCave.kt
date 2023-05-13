package com.example.explorelanka.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.explorelanka.R
import com.example.explorelanka.models.SigiriyaModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RavanaCave : AppCompatActivity() {

    private lateinit var etChildrenTicketPlaceRavanaCave: EditText
    private lateinit var etAdultTicketPlaceRavanaCave: EditText
    private lateinit var etTypePlaceRavanaCave: EditText
    private lateinit var etNamePlaceRavanaCave: EditText
    private lateinit var btnBookingRavanaCave: Button
    private lateinit var btnViewRavanaCave: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ravana_cave)

        etChildrenTicketPlaceRavanaCave = findViewById<EditText>(R.id.edt_children_no_ravana)
        etAdultTicketPlaceRavanaCave = findViewById<EditText>(R.id.edt_adults_no_ravana)
        etTypePlaceRavanaCave = findViewById<EditText>(R.id.edt_lf_ravana)
        etNamePlaceRavanaCave = findViewById<EditText>(R.id.enterNameRavana)
        btnBookingRavanaCave = findViewById<Button>(R.id.btn_ticket_ravana)
        btnViewRavanaCave = findViewById<Button>(R.id.btnRead_ravana)

        dbRef = FirebaseDatabase.getInstance().getReference("Places")

        btnBookingRavanaCave.setOnClickListener {
            BookRavanaTickets()
        }

        btnViewRavanaCave.setOnClickListener {
            val intent = Intent(this, Fetching::class.java)
            startActivity(intent)
        }
    }

    private fun BookRavanaTickets() {

        // getting values
        val noChildTicketRavanaCave = etChildrenTicketPlaceRavanaCave.text.toString()
        val noAdultTicketRavanaCave = etAdultTicketPlaceRavanaCave.text.toString()
        val lfTypeRavanaCave = etTypePlaceRavanaCave.text.toString()
        val nameRavanaCave = etNamePlaceRavanaCave.text.toString()

        if (noChildTicketRavanaCave.isEmpty()) {
            etChildrenTicketPlaceRavanaCave.error = "Please Enter Number of Children Tickets"
            return
        }

        if (noAdultTicketRavanaCave.isEmpty()) {
            etAdultTicketPlaceRavanaCave.error = "Please Enter Number of Adults Tickets"
            return
        }

        if (lfTypeRavanaCave.isEmpty()) {
            etTypePlaceRavanaCave.error = "Please Enter Type of Tourist Tickets"
            return
        }

        if (nameRavanaCave.isEmpty()) {
            etNamePlaceRavanaCave.error = "Please Enter Name"
            return
        }

        val ravanaCaveId = dbRef.push().key!!

        val ravanaCave = SigiriyaModel(ravanaCaveId, noChildTicketRavanaCave, noAdultTicketRavanaCave, lfTypeRavanaCave, nameRavanaCave)

        dbRef.child(ravanaCaveId).setValue(ravanaCave).addOnCompleteListener{
            Toast.makeText(this, "Ravana Cave Booking Successfully", Toast.LENGTH_LONG).show()

            etChildrenTicketPlaceRavanaCave.text.clear()
            etAdultTicketPlaceRavanaCave.text.clear()
            etTypePlaceRavanaCave.text.clear()
            etNamePlaceRavanaCave.text.clear()

        }.addOnFailureListener{err->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}