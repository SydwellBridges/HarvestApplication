package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_soiltest.*

class SoiltestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soiltest)

        readMoreTextView.setOnClickListener {

            startActivity(Intent(this, readMoreActivity::class.java))
        }
    }
}