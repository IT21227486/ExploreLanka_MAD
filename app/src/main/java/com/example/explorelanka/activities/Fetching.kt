package com.example.explorelanka.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explorelanka.R
import com.example.explorelanka.adapters.TuoAdapter
import com.example.explorelanka.models.SigiriyaModel
import com.google.firebase.database.*

class Fetching : AppCompatActivity() {

    private lateinit var tuoRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var tuoList: ArrayList<SigiriyaModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        tuoRecyclerView = findViewById(R.id.rvtuorist)
        tuoRecyclerView.layoutManager = LinearLayoutManager(this)
        tuoRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        tuoList = arrayListOf<SigiriyaModel>()

        getTuoristsData()
    }

    private fun getTuoristsData() {
        tuoRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Places")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                tuoList.clear()
                if(snapshot.exists()) {
                    for(tuoSnap in snapshot.children){
                        val tuoData = tuoSnap.getValue(SigiriyaModel::class.java)
                        tuoList.add(tuoData!!)
                    }
                    val mAdapter = TuoAdapter(tuoList)
                    tuoRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : TuoAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@Fetching, tourist_details::class.java)

                            //put extras
//                            intent.putExtra("sigiriyaId", tuoList[position].sigiriyaId)
                            intent.putExtra("sigiriyaId", tuoList[position].sigiriyaId)
                            intent.putExtra("childrenTicketSigiriya", tuoList[position].childrenTicketSigiriya)
                            intent.putExtra("adultTicketSigiriya", tuoList[position].adultTicketSigiriya)
                            intent.putExtra("typeSigiriya", tuoList[position].typeSigiriya)
                            intent.putExtra("nameSigiriya", tuoList[position].nameSigiriya)
                            startActivity(intent)
                        }

                    })

                    tuoRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}