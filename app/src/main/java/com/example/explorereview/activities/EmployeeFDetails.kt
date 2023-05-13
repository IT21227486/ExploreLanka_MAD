package com.example.explorereview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.explorereview.R

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
}