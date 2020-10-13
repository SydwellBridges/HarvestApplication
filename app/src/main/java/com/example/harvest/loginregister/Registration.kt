package com.example.harvest.loginregister

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.harvest.R
import com.example.harvest.dashboard.MainMenuScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

        //val backButton2 = findViewById<ImageView>(R.id.backView2)
        val loginUser = findViewById<TextView>(R.id.loginTextView)

        // Register user
        registerButton.setOnClickListener {
            performRegister()
        }

        // Navigate to login activity
        loginUser.setOnClickListener{

            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // Upload profile picture
        selectphotoButtonRegister.setOnClickListener {
            Log.d("Registration", "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    // method called when photo selector intent is finished
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            Log.d("RegisterActivity", "Photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            selectphotoButtonRegister.setBackgroundDrawable(bitmapDrawable)
        }
    }

    // Method to perform registration
    private fun performRegister(){

        val name = editTextName.text.toString()
        val email = editTextTextEmailAddress.text.toString()
        val phone = editTextPhone.text.toString()
        val password = editTextPassword.text.toString()

        // Log a debug message
        Log.d("Registration", "Email is: $email")
        Log.d("Registration", "Password is: $password")

        // validate the use details
        if(email.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        // register user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "User created successfully, please verify Email before Login ", Toast.LENGTH_LONG).show()

                    val user = Firebase.auth.currentUser

                    // verify user email
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