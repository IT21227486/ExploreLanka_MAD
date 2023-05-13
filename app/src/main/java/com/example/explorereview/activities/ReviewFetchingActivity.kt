package com.example.explorereview.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explorereview.R
import com.example.explorereview.adapters.ReviewAdapter
import com.example.explorereview.models.FeedBackModel
import com.google.firebase.database.*

class ReviewFetchingActivity : AppCompatActivity() {

    private lateinit var feedbackRecyclerView: RecyclerView
    private lateinit var tvRLoadingData: TextView
    private lateinit var feedbackList: ArrayList<FeedBackModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_fetching)

        feedbackRecyclerView = findViewById(R.id.rvEmp)
        feedbackRecyclerView.layoutManager = LinearLayoutManager(this)
        feedbackRecyclerView.setHasFixedSize(true)
        tvRLoadingData = findViewById(R.id.tvFLoadingData)

        feedbackList = arrayListOf<FeedBackModel>()

        getFeedbackData()

    }

    private fun getFeedbackData() {
        feedbackRecyclerView.visibility = View.GONE
        tvRLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                feedbackList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val feedbackData = empSnap.getValue(FeedBackModel::class.java)
                        feedbackList.add(feedbackData!!)
                    }
                    val rAdapter = ReviewAdapter(feedbackList)
                    feedbackRecyclerView.adapter = rAdapter

                    rAdapter.setOnItemClickListener(object : ReviewAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@ReviewFetchingActivity, EmployeeFDetails::class.java)

                            //put extras
                            intent.putExtra("feedbackId", feedbackList[position].feedbackId)
                            intent.putExtra("feedbackName", feedbackList[position].feedbackName)
                            intent.putExtra("feedbackMsg", feedbackList[position].feedbackMsg)
                            startActivity(intent)
                        }

                    })

                    feedbackRecyclerView.visibility = View.VISIBLE
                    tvRLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}