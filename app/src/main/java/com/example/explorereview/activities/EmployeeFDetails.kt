package com.example.explorereview.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.explorereview.R
import com.example.explorereview.models.FeedBackModel
import com.google.firebase.database.FirebaseDatabase

class EmployeeFDetails : AppCompatActivity() {

    private lateinit var tvFEmpId: TextView
    private lateinit var tvFEmpName: TextView
    private lateinit var tvFEmpMsg: TextView
    private lateinit var btnFUpdate: Button
    private lateinit var btnFDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_fdetails)

        initView()
        setValuesToViews()

        btnFUpdate.setOnClickListener {
            openUpdateReviewDialog(
                intent.getStringExtra("feedbackId").toString(),
                intent.getStringExtra("feedbackName").toString()
            )
        }

        btnFDelete.setOnClickListener{
            deleteFeedBackRecord(
                intent.getStringExtra("feedbackId").toString()
            )
        }
    }

    private fun deleteFeedBackRecord(
        fid: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks").child(fid)
        val fTask = dbRef.removeValue()

        fTask.addOnSuccessListener {
            Toast.makeText(this, "Feedback data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ReviewFetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvFEmpId = findViewById(R.id.tvFEmpId)
        tvFEmpName = findViewById(R.id.tvFEmpName)
        tvFEmpMsg = findViewById(R.id.tvFEmpMsg)

        btnFUpdate = findViewById(R.id.btnFUpdate)
        btnFDelete = findViewById(R.id.btnFDelete)
    }

    private fun setValuesToViews() {
        tvFEmpId.text = intent.getStringExtra("feedbackId")
        tvFEmpName.text = intent.getStringExtra("feedbackName")
        tvFEmpMsg.text = intent.getStringExtra("feedbackMsg")

    }

    //@SuppressLint("MissingInflatedId")
    private fun openUpdateReviewDialog(
        feedbackId: String,
        feedbackName: String
    ){
        val fDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val fDialogView = inflater.inflate(R.layout.activity_update_dialog, null)

        fDialog.setView(fDialogView)

        val etFEmpName = fDialogView.findViewById<EditText>(R.id.etFREmpName)
        val etFEmpMsg = fDialogView.findViewById<EditText>(R.id.etFREmpMag)
        val btnUpdateData = fDialogView.findViewById<Button>(R.id.btnFRUpdateData)

        etFEmpName.setText(intent.getStringExtra("feedbackName").toString())
        etFEmpMsg.setText(intent.getStringExtra("feedbackMsg").toString())

        fDialog.setTitle("Updating $feedbackName Record")

        val alertDialog = fDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateFeedbackData(
                feedbackId,
                etFEmpName.text.toString(),
                etFEmpMsg.text.toString()
            )

            Toast.makeText(applicationContext, "Feedback Data Updated.", Toast.LENGTH_LONG).show()

            // we are setting updated data to our textviews
            tvFEmpName.text = etFEmpName.text.toString()
            tvFEmpMsg.text = etFEmpMsg.text.toString()

            alertDialog.dismiss()

        }

    }

    private fun updateFeedbackData(
        fid: String,
        fname: String,
        fmsg: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks").child(fid)
        val empInfo = FeedBackModel(fid, fname, fmsg)
        dbRef.setValue(empInfo)
    }
}