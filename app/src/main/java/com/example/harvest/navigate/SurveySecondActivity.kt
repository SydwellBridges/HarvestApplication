package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_survey_second.*

class SurveySecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_second)

        displayResults.setOnClickListener {

            startActivity(Intent(this, SurveyResultsActivity::class.java))
            finish()
        }

    }
}