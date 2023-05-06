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

class sigiriya : AppCompatActivity() {

    private lateinit var etChildrenTicketPlaceSigiriya: EditText
    private lateinit var etAdultTicketPlaceSigiriya: EditText
    private lateinit var etTypePlaceSigiriya: EditText
    private lateinit var etNamePlaceSigiriya: EditText
    private lateinit var btnBookingSigiriya: Button
    private lateinit var btnViewSigiriya: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigiriya)

        etChildrenTicketPlaceSigiriya = findViewById<EditText>(R.id.edt_children_no_sigiriya)
        etAdultTicketPlaceSigiriya = findViewById<EditText>(R.id.edt_adults_no_sigiriya)
        etTypePlaceSigiriya = findViewById<EditText>(R.id.edt_lf_sigiriya)
        etNamePlaceSigiriya = findViewById<EditText>(R.id.enterNameSigiriya)
        btnBookingSigiriya = findViewById<Button>(R.id.btn_ticket_sigiriya)
        btnViewSigiriya = findViewById<Button>(R.id.btnRead_sigiriya)

        dbRef = FirebaseDatabase.getInstance().getReference("Places")

        btnBookingSigiriya.setOnClickListener {
            BookSigiriyaTickets()
        }

        btnViewSigiriya.setOnClickListener {
            val intent = Intent(this, Fetching::class.java)
            startActivity(intent)
        }



    }

    private fun BookSigiriyaTickets() {

        // getting values
        val noChildTicketSigiriya = etChildrenTicketPlaceSigiriya.text.toString()
        val noAdultTicketSigiriya = etAdultTicketPlaceSigiriya.text.toString()
        val lfTypeSigiriya = etTypePlaceSigiriya.text.toString()
        val nameSigiriya = etNamePlaceSigiriya.text.toString()

        if (noChildTicketSigiriya.isEmpty()) {
            etChildrenTicketPlaceSigiriya.error = "Please Enter Number of Children Tickets"
            return
        }

        if (noAdultTicketSigiriya.isEmpty()) {
            etAdultTicketPlaceSigiriya.error = "Please Enter Number of Adults Tickets"
            return
        }

        if (lfTypeSigiriya.isEmpty()) {
            etTypePlaceSigiriya.error = "Please Enter Type of Tourist Tickets"
            return
        }

        if (nameSigiriya.isEmpty()) {
            etNamePlaceSigiriya.error = "Please Enter Name"
            return
        }

        val sigiriyaId = dbRef.push().key!!

        val sigiriya = SigiriyaModel(sigiriyaId, noChildTicketSigiriya, noAdultTicketSigiriya, lfTypeSigiriya, nameSigiriya)

        dbRef.child(sigiriyaId).setValue(sigiriya).addOnCompleteListener{
            Toast.makeText(this, "Sigiriya Booking Successfully", Toast.LENGTH_LONG).show()

            etChildrenTicketPlaceSigiriya.text.clear()
            etAdultTicketPlaceSigiriya.text.clear()
            etTypePlaceSigiriya.text.clear()
            etNamePlaceSigiriya.text.clear()

        }.addOnFailureListener{err->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }

}