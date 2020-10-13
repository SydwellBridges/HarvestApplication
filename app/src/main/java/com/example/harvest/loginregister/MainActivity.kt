package com.example.harvest.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.harvest.R
import com.example.harvest.dashboard.MainMenuScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeButton = findViewById<Button>(R.id.welcomeBtn)
        val register = findViewById<TextView>(R.id.registerTextView)

        // Go to login activity
        welcomeButton.setOnClickListener{

            startActivity(Intent(this, Login::class.java))
        }

        // Go to register activity
        register.setOnClickListener {

            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }

}