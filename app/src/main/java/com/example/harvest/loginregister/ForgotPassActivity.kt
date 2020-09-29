package com.example.harvest.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.harvest.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity() {
    lateinit var mEmail : EditText
    lateinit var mForgetBtn : Button

    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        mEmail = findViewById(R.id.forgetEmail)
        mForgetBtn = findViewById(R.id.forgetBtn)

        mAuth = FirebaseAuth.getInstance()



        mForgetBtn.setOnClickListener {
            val email = mEmail.text.toString().trim()

            if(email.isEmpty()){
                mEmail.error = "Enter Email"
                return@setOnClickListener
            }

            forgetPassword(email)
        }

    }

    private fun forgetPassword(email: String) {

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task: Task<Void> ->
            if(task.isComplete){
                val intent = Intent(this, Login::class.java)
                startActivity(intent)

                Toast.makeText(applicationContext , "Please check your emails and reset password", Toast.LENGTH_LONG).show()
            }
        }

    }
}