package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payment_for_explorelanka.PayModel.PaymentModel
import com.example.payment_for_explorelanka.payAdapters.PayAdapter
import com.google.firebase.database.*

class FetchPaymentData : AppCompatActivity() {

    private lateinit var payRecyclerView : RecyclerView
    private lateinit var tvPayLoadingData : TextView
    private lateinit var payList: ArrayList<PaymentModel>
    private lateinit var payDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_payment_data)


        payRecyclerView = findViewById(R.id.rvPayInfo)
        payRecyclerView.layoutManager = LinearLayoutManager(this)
        payRecyclerView.setHasFixedSize(true)
        tvPayLoadingData = findViewById(R.id.tvPayLoadingData)

        payList = arrayListOf<PaymentModel>()

        getPaymentData()

    }

    private fun getPaymentData(){
        payRecyclerView.visibility = View.GONE
        tvPayLoadingData.visibility = View.VISIBLE

        payDbRef = FirebaseDatabase.getInstance().getReference("Payments")

        payDbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                payList.clear()

                if (snapshot.exists()){
                    for(paySnap in snapshot.children){
                        val payData = paySnap.getValue(PaymentModel::class.java)
                        payList.add(payData!!)
                    }
                    val pAdapter = PayAdapter(payList)
                    payRecyclerView.adapter = pAdapter

                    pAdapter.setOnItemClickListener(object : PayAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchPaymentData, PaymentDetails::class.java)
                            //put extra
                            intent.putExtra("cusID", payList[position].Customerid)
                            intent.putExtra("cusName", payList[position].CustomerName)
                            intent.putExtra("cusNo", payList[position].CustomerNum)
                            intent.putExtra("pay_Type", payList[position].payType)
                            intent.putExtra("booking_Type", payList[position].bookType)
                            intent.putExtra("amount", payList[position].amount)

                            startActivity(intent)
                        }

                    })

                    payRecyclerView.visibility = View.VISIBLE
                    tvPayLoadingData.visibility = View.GONE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                //nothing
            }

        })
    }
}