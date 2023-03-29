package com.example.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddContact : AppCompatActivity() {
    lateinit var databaseContact : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        val ctNumber = findViewById<TextInputEditText>(R.id.ctNumber)
        val ctEmail = findViewById<TextInputEditText>(R.id.ctEmail)
        val ctName = findViewById<TextInputEditText>(R.id.ctName)
        val addContact = findViewById<Button>(R.id.btnAdd)

        addContact.setOnClickListener {
            val name = ctName.text.toString()
            val mail = ctEmail.text.toString()
            val num = ctNumber.text.toString()
            val contact = Contacts(name,mail,num)
            databaseContact= FirebaseDatabase.getInstance().getReference("Contacts")
            databaseContact.child(num).setValue(contact).addOnSuccessListener {
                ctName.text?.clear()
                ctEmail.text?.clear()
                ctNumber.text?.clear()
                Toast.makeText(this,"Contact Added" ,Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed" ,Toast.LENGTH_SHORT).show()
            }
        }
    }
}