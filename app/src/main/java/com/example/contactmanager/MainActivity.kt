package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName= findViewById<TextInputEditText>(R.id.etName)
        val etMail= findViewById<TextInputEditText>(R.id.etMail)
        val userId= findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val signUpButton = findViewById<Button>(R.id.btnSignUp)

        signUpButton.setOnClickListener {
            val name= etName.text.toString()
            val mail= etMail.text.toString()
            val uniqueID= userId.text.toString()
            val password= userPassword.text.toString()
            val user=Users(name,mail,uniqueID,password)
            database= FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueID).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                userId.text?.clear()
                userPassword.text?.clear()
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }

        val signIn = findViewById<TextView>(R.id.tVSignIn)
        signIn.setOnClickListener {
            intent=Intent(this,SignIn::class.java)
            startActivity(intent)
        }
    }
}