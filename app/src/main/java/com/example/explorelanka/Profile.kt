package com.example.explorelanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.explorelanka.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Profile : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        etFirstName = findViewById(R.id.editTextTextPersonName3)
        etLastName = findViewById(R.id.editTextTextPersonName4)
        etEmail = findViewById(R.id.editTextTextPersonName6)
        etPassword = findViewById(R.id.editTextTextPersonName7)

        firebaseDatabase = FirebaseDatabase.getInstance()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            databaseReference = firebaseDatabase.reference.child("Users").child(currentUser.uid)
        } else {
            // Handle the case when the user is not authenticated
            // For example, redirect to login screen
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
            return
        }

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val firstName = dataSnapshot.child("fname").getValue(String::class.java)
                    val lastName = dataSnapshot.child("lname").getValue(String::class.java)
                    val email = dataSnapshot.child("uemail").getValue(String::class.java)
                    val password = dataSnapshot.child("upassword").getValue(String::class.java)

                    etFirstName.setText(firstName)
                    etLastName.setText(lastName)
                    etEmail.setText(email)
                    etPassword.setText(password)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
                Toast.makeText(this@Profile, "Failed to retrieve profile information", Toast.LENGTH_SHORT).show()
            }
        })

        val updateButton = findViewById<Button>(R.id.editProfileButton)
        updateButton.setOnClickListener {
            val updatedFirstName = etFirstName.text.toString().trim()
            val updatedLastName = etLastName.text.toString().trim()
            val updatedEmail = etEmail.text.toString().trim()
            val updatedPassword = etPassword.text.toString().trim()

            val userUpdates = hashMapOf<String, Any>(
                "fname" to updatedFirstName,
                "lname" to updatedLastName,
                "uemail" to updatedEmail,
                "upassword" to updatedPassword
            )

            databaseReference.updateChildren(userUpdates)
                .addOnSuccessListener {
                    Toast.makeText(this@Profile, "Profile updated successfully", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this@Profile, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
        }

        val deleteButton = findViewById<Button>(R.id.deleteProfileButton)
        deleteButton.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.delete()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        databaseReference.removeValue()
                        Toast.makeText(this@Profile, "Profile deleted successfully", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@Profile, LogIn::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
        }
    }
}
