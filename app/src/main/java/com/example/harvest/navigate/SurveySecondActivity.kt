package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.harvest.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_survey_second.*

class SurveySecondActivity : AppCompatActivity() {

    lateinit var typeSoil : EditText
    lateinit var phLevel : EditText
    lateinit var textureSoil : EditText
    lateinit var colourSoil : EditText
    lateinit var porosity : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_second)

        typeSoil = findViewById(R.id.soilType)
        phLevel  = findViewById(R.id.editTextPH)
        textureSoil = findViewById(R.id.soilTexture)
        colourSoil = findViewById(R.id.soilColour)
        porosity = findViewById(R.id.soilPorosity)

        displayResults.setOnClickListener {

            saveData()

            startActivity(Intent(this, SurveyResultsActivity::class.java))
            finish()
        }

    }

    private fun saveData(){

        val soiltype = typeSoil.text.toString().trim()
        val phlevel = phLevel.text.toString().trim()
        val soiltexture = textureSoil.text.toString().trim()
        val soilcolour = colourSoil.text.toString().trim()
        val soilPorosity = porosity.text.toString().trim()

        if(soiltype.isEmpty())
        {
            soilType.error = "Please enter a soil type"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Soil Properties")

        // create new node inside survey node - push() - generates a new key inside Survey node
        val surveyId = ref.push().key

        // create a survey object
        val soil = surveyId?.let { soilProperties(it, soiltype, phlevel, soiltexture, soilcolour, soilPorosity) }

        if (surveyId != null) {
            ref.child(surveyId).setValue(soil).addOnCompleteListener{

                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            }
        }


    }
}