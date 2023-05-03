package com.example.explorelanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class sigiriya : AppCompatActivity() {

    private lateinit var etChildrenTicketPlaceSigiriya: EditText
    private lateinit var etAdultTicketPlaceSigiriya: EditText
    private lateinit var etTypePlaceSigiriya: EditText
    private lateinit var btnBooking: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigiriya)

        etChildrenTicketPlaceSigiriya = findViewById(R.id.edt_children_no_sigiriya)
        etAdultTicketPlaceSigiriya = findViewById(R.id.edt_adults_no_sigiriya)
        etTypePlaceSigiriya = findViewById(R.id.edt_lf_sigiriya)
        btnBooking = findViewById(R.id.btn_ticket_sigiriya)

        dbRef = FirebaseDatabase.getInstance().getReference("Places")

        btnBooking.setOnClickListener() {
            BookSigiriyaTickets()
        }

    }

    private fun BookSigiriyaTickets() {

        // getting values
        val noChildTicketSigiriya = etChildrenTicketPlaceSigiriya.text.toString()
        val noAdultTicketSigiriya = etAdultTicketPlaceSigiriya.text.toString()
        val lfTypeSigiriya = etTypePlaceSigiriya.text.toString()

        if (noChildTicketSigiriya.isEmpty()) {
            etChildrenTicketPlaceSigiriya.error = "Please Enter Number of Children Tickets"
        }

        if (noAdultTicketSigiriya.isEmpty()) {
            etAdultTicketPlaceSigiriya.error = "Please Enter Number of Adults Tickets"
        }

        if (lfTypeSigiriya.isEmpty()) {
            etTypePlaceSigiriya.error = "Please Enter Type of Tourist Tickets"
        }

    }

}