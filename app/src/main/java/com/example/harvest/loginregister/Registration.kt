package com.example.harvest.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.harvest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val backButton2 = findViewById<ImageView>(R.id.backView2)
        val loginUser = findViewById<TextView>(R.id.loginTextView)

        // Register user
        registerButton.setOnClickListener {
            performRegister()
        }

        // Navigate back to login activity
        backButton2.setOnClickListener{

            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // Navigate to login activity
        loginUser.setOnClickListener{

            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    // Method to perform registration
    private fun performRegister(){

        val name = editTextName.text.toString()
        val email = editTextTextEmailAddress.text.toString()
        val phone = editTextPhone.text.toString()
        val password = editTextPassword.text.toString()

        // validate the use details
        if(email.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "User created successfully, please verify Email before Login ", Toast.LENGTH_LONG).show()

                    val user = Firebase.auth.currentUser

                    user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("verify email", "Email sent.")
                                }
                            }

                    // Go to login activity
                    startActivity(Intent(this, Login::class.java))
                    finish()

                } else {

                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Sign up failed. Try again later", Toast.LENGTH_SHORT).show()
                }
            }
    }


}