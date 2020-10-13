package com.example.harvest.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.harvest.dashboard.MainMenuScreen
import com.example.harvest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize firebase auth
        auth = FirebaseAuth.getInstance()

        val backButton = findViewById<ImageView>(R.id.backView)
        val register = findViewById<TextView>(R.id.registerTextView2)
        val loginUser = findViewById<Button>(R.id.loginButton)

        // Go back to MainActivity - Welcome screen
        backButton.setOnClickListener{

            startActivity(Intent(this, MainActivity::class.java))
        }

        // Go to registration activity
        register.setOnClickListener{

            intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        // login the user
        loginUser.setOnClickListener {

            performLogin()
        }

        textViewForgotLabel.setOnClickListener {
            intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)

        }


    }

    // Login the user to Main Menu
    private fun performLogin(){

        Log.d("Login", "Attempting to login with email and password")

        val email = editTextEmailAddressLogin.text.toString()
        val password = editTextPasswordLogin.text.toString()

        // validate the use details
        if(email.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        // Sign in user with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)

                } else {

                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser : FirebaseUser?){

        // If user is not null then go to the MainMenu Activity (Login is successful)
        if (currentUser != null){
            if(currentUser.isEmailVerified) {

                startActivity(Intent(this, MainMenuScreen::class.java))
            }

        }else{

            // If sign in fails, display a message to the user.
            Toast.makeText(baseContext, "Login failed. Please verify user", Toast.LENGTH_SHORT).show()
        }

    }

}