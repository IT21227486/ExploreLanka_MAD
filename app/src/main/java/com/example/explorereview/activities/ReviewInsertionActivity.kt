package com.example.explorereview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.explorereview.R
import com.example.explorereview.models.FeedBackModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReviewInsertionActivity : AppCompatActivity() {

    private lateinit var etREmpName: EditText
    private lateinit var etREmpMsg: EditText
    private lateinit var etRSaveData: Button

    private lateinit var dbRef: DatabaseReference

    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_insertion)

        etREmpName = findViewById(R.id.etREmpName)
        etREmpMsg = findViewById(R.id.etREmpMsg)
        etRSaveData = findViewById(R.id.btnReviewSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        etRSaveData.setOnClickListener{
            saveFeedbackData()
        }
    }

    private fun saveFeedbackData() {

        //getting values
        val RempName = etREmpName.text.toString()
        val RempMsg = etREmpMsg.text.toString()

        if(RempName.isEmpty()){
            etREmpName.error = "Please Enter Name"
            return
        }

        if(RempMsg.isEmpty()){
            etREmpMsg.error = "Please Enter Name"
            return
        }

        val FeedId = dbRef.push().key!!

        val feedback = FeedBackModel(FeedId, RempName, RempMsg)

        dbRef.child(FeedId).setValue(feedback)
            .addOnCompleteListener {
                Toast.makeText(this, "FeedBack inserted successfully", Toast.LENGTH_LONG).show()

                etREmpName.text.clear()
                etREmpMsg.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}