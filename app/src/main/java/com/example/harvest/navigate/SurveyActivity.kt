package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.harvest.R
import com.google.firebase.database.FirebaseDatabase


class SurveyActivity : AppCompatActivity() {

    lateinit var areaLocation : EditText
    lateinit var areaSize : EditText
    lateinit var typeOfLand : EditText
    lateinit var typeOfSlope : EditText
    lateinit var accessDistance : EditText
    lateinit var textShade : EditText

    lateinit var typeSoil : EditText
    lateinit var phLevel : EditText
    lateinit var textureSoil : EditText
    lateinit var colourSoil : EditText
    lateinit var porosity : EditText

    lateinit var SaveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_acivity)

        areaLocation = findViewById(R.id.areaLocation)
        areaSize = findViewById(R.id.areaSize)
        typeOfLand = findViewById(R.id.landType)
        typeOfSlope = findViewById(R.id.slopeType)
        accessDistance = findViewById(R.id.roadDistance)
        textShade = findViewById(R.id.editTextShade)

        typeSoil = findViewById(R.id.soilType)
        phLevel  = findViewById(R.id.editTextPH)
        textureSoil = findViewById(R.id.soilTexture)
        colourSoil = findViewById(R.id.soilColour)
        porosity = findViewById(R.id.soilPorosity)

        SaveButton = findViewById(R.id.SavButton)

        // Button to save data to database and continue to the next form
        SaveButton.setOnClickListener {

            saveSurvey()
            startActivity(Intent(this, SurveyResultsActivity::class.java))

        }
    }

    private fun saveSurvey(){

        val location = areaLocation.text.toString().trim()
        val size = areaSize.text.toString().toInt()
        val land = typeOfLand.text.toString().trim()
        val slope = typeOfSlope.text.toString().trim()
        val distanceRoad = accessDistance.text.toString().toInt()
        val shade = textShade.text.toString().trim()

        val soiltype = typeSoil.text.toString().trim()
        val phlevel = phLevel.text.toString().toInt()
        val soiltexture = textureSoil.text.toString().trim()
        val soilcolour = colourSoil.text.toString().trim()
        val soilPorosity = porosity.text.toString().trim()

        if(location.isEmpty()){

            areaLocation.error = "Please enter location"
            return
        }

        if(shade.isEmpty()){

            textShade.error = "Please enter shade type"
            return
        }

        if(soiltype.isEmpty())
        {
            typeSoil.error = "Please enter a soil type"
            return
        }


        // Database reference object
        val ref = FirebaseDatabase.getInstance().getReference("Land Properties")

        // create new node inside survey node - push() - generates a new key inside Survey node
        val surveyId = ref.push().key

        // create a survey object
        val survey = surveyId?.let { Survey(it, location, size, land, slope, distanceRoad, shade, soiltype, phlevel, soiltexture, soilcolour, soilPorosity) }

        if (surveyId != null) {
            ref.child(surveyId).setValue(survey).addOnCompleteListener{

                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            }
        }

    }
}