package com.example.payment_for_explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.payment_for_explorelanka.PayModel.PaymentModel
import com.google.firebase.database.FirebaseDatabase

class PaymentDetails : AppCompatActivity() {

    private lateinit var tvCusID: TextView
    private lateinit var tvCustName: TextView
    private lateinit var tvCusPhonNo: TextView
    private lateinit var tvpayType: TextView
    private lateinit var tvbookType: TextView
    private lateinit var tvamount: TextView
    private lateinit var btnDelete: Button
    private lateinit var btnUpdate: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cusId").toString(),
                intent.getStringExtra("cusName").toString()

            )
        }

        btnDelete.setOnClickListener {
            deletePayData(
                intent.getStringExtra("cusId").toString(),
            )
        }

    }


    private fun initView(){

        tvCusID = findViewById(R.id.tvCusId)
        tvCustName = findViewById(R.id.tvCustomerName)
        tvCusPhonNo = findViewById(R.id.tvCUsNo)
        tvpayType = findViewById(R.id.tvPaytype)
        tvbookType = findViewById(R.id.tvBookType)
        tvamount = findViewById(R.id.tvPayAmount)
        // and so on for the rest of the views
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }



    private fun setValuesToViews(){

        tvCusID.text = intent.getStringExtra("cusId")
        tvCustName.text = intent.getStringExtra("cusName")
        tvCusPhonNo.text = intent.getStringExtra("cusNo")
        tvpayType.text = intent.getStringExtra("pay_Type")
        tvbookType.text = intent.getStringExtra("booking_Type")
        tvamount.text = intent.getStringExtra("amount")
    }



    private fun deletePayData(
        cId: String
    ){
        val payDbRef = FirebaseDatabase.getInstance().getReference("Payments").child(cId)
        val pTask = payDbRef.removeValue()

        pTask.addOnSuccessListener {
            Toast.makeText(this, "Payment Data Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchPaymentData::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun openUpdateDialog(
        cusId : String,
        cusName: String
    ){
        val pDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val pDialogView = inflater.inflate(R.layout.update_paydialog, null)

        pDialog.setView(pDialogView)

        val etCusName = pDialogView.findViewById<EditText>(R.id.customerNameUp)
        val etCusNo = pDialogView.findViewById<EditText>(R.id.customerNumberUp)
        val etPayType = pDialogView.findViewById<EditText>(R.id.payTypeUp)
        val etBookType = pDialogView.findViewById<EditText>(R.id.bookTypeUp)
        val etAmount = pDialogView.findViewById<EditText>(R.id.amountUp)
        val btnUpdatePay = pDialogView.findViewById<Button>(R.id.save_buttonUp)

        etCusName.setText(intent.getStringExtra("cusName").toString())
        etCusNo.setText(intent.getStringExtra("cusNo").toString())
        etPayType.setText(intent.getStringExtra("pay_Type").toString())
        etBookType.setText(intent.getStringExtra("booking_Type").toString())
        etAmount.setText(intent.getStringExtra("amount").toString())


        pDialog.setTitle("Updating $cusName Record")

        val alertPayDialog = pDialog.create()
        alertPayDialog.show()


        btnUpdatePay.setOnClickListener {
            updateCusData(
                cusId,
                etCusName.text.toString(),
                etCusNo.text.toString(),
                etPayType.text.toString(),
                etBookType.text.toString(),
                etAmount.text.toString()
            )

            Toast.makeText(applicationContext, "Customer Data updated ", Toast.LENGTH_LONG).show()

            //we ar setting updated data
            tvCustName.text = etCusName.text.toString()
            tvCusPhonNo.text = etCusNo.text.toString()
            tvpayType.text = etPayType.text.toString()
            tvbookType.text = etPayType.text.toString()
            tvamount.text = etAmount.text.toString()

            alertPayDialog.dismiss()


        }

    }

    private fun updateCusData(
        cId: String,
        cName:String,
        cNum: String,
        cPayType:String,
        cBookType: String,
        cAmount: String
    ){
        val payDbRef = FirebaseDatabase.getInstance().getReference("Payments").child(cId)
        val payInfo = PaymentModel(cId, cName, cNum, cPayType, cBookType, cAmount)
        payDbRef.setValue(payInfo)
    }

}