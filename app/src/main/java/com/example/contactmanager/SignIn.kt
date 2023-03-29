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

class SignIn : AppCompatActivity() {
    private lateinit var databaseRefrence: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.loginDetail)
        val SignUp = findViewById<TextView>(R.id.tVSignUp)

        SignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val userNameString = userName.text.toString()

            if(userNameString.isNotEmpty()){
                readData(userNameString)
            }
            else{
                Toast.makeText(this,"Please Enter user name" , Toast.LENGTH_SHORT).show()
            }
       }
   }
   private fun readData(userNameString:String){
       databaseRefrence = FirebaseDatabase.getInstance().getReference("Users")
       databaseRefrence.child(userNameString).get().addOnSuccessListener {
           if(it.exists()){
               val intent = Intent(this, AddContact::class.java)
               startActivity(intent)
           }
           else{
               Toast.makeText(this,"User is not registered",Toast.LENGTH_SHORT).show()
           }
       }
   }
}