package com.example.explorelanka.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.explorelanka.R
import com.example.explorelanka.models.SigiriyaModel
import com.google.firebase.database.FirebaseDatabase

class tourist_details : AppCompatActivity() {

    private lateinit var tvTuoId: TextView
    private lateinit var tvTuoName: TextView
    private lateinit var tvNoChild: TextView
    private lateinit var tvNoAdult: TextView
    private lateinit var tvType: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("sigiriyaId").toString(),
                intent.getStringExtra("nameSigiriya").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("sigiriyaId").toString()
            )
        }

    }

    private fun initView() {
        tvTuoId = findViewById(R.id.sigiriyaId)
        tvTuoName = findViewById(R.id.enterNameSigiriya)
        tvNoChild = findViewById(R.id.edt_children_no_sigiriya)
        tvNoAdult = findViewById(R.id.edt_adults_no_sigiriya)
        tvType = findViewById(R.id.LF_sigiriya)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvTuoId.text = intent.getStringExtra("sigiriyaId")
        tvNoChild.text = intent.getStringExtra("childrenTicketSigiriya")
        tvNoAdult.text = intent.getStringExtra("adultTicketSigiriya")
        tvType.text = intent.getStringExtra("typeSigiriya")
        tvTuoName.text = intent.getStringExtra("nameSigiriya")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Places").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Sigiriya Ticket Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Fetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }



    private fun openUpdateDialog(
        sigiriyaId: String,
        nameSigiriya: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog, null)

        mDialog.setView(mDialogView)

//        val etSTuoName = mDialogView.findViewById<EditText>(R.id.etSTuoName)
        val etSTuoChild = mDialogView.findViewById<EditText>(R.id.etSTuoChild)
        val etSTuoadult = mDialogView.findViewById<EditText>(R.id.etSTuoadult)
        val etSTuoType = mDialogView.findViewById<EditText>(R.id.etSTuoType)
        val etSTuoName = mDialogView.findViewById<EditText>(R.id.etSTuoName)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

//        etSTuoName.setText(intent.getStringExtra("nameSigiriya").toString())
        etSTuoChild.setText(intent.getStringExtra("childrenTicketSigiriya").toString())
        etSTuoadult.setText(intent.getStringExtra("adultTicketSigiriya").toString())
        etSTuoType.setText(intent.getStringExtra("typeSigiriya").toString())
        etSTuoName.setText(intent.getStringExtra("nameSigiriya").toString())


        mDialog.setTitle("Updating $nameSigiriya Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateTuoData(
                sigiriyaId,
                etSTuoChild.text.toString(),
                etSTuoadult.text.toString(),
                etSTuoType.text.toString(),
                etSTuoName.text.toString()
            )

            Toast.makeText(applicationContext, "Tourist Ticket Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvNoChild.text = etSTuoChild.text.toString()
            tvNoAdult.text = etSTuoadult.text.toString()
            tvType.text = etSTuoType.text.toString()
            tvTuoName.text = etSTuoName.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateTuoData(
        id: String,
//        name: String,
        childT: String,
        adultT: String,
        type: String,
        name: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Places").child(id)

        val tuoInfo = SigiriyaModel(id, childT, adultT, type, name)

        dbRef.setValue(tuoInfo)
    }
}