package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.harvest.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_survey_acivity.*

class SurveyAcivity : AppCompatActivity() {

    lateinit var areaLocation : EditText
    lateinit var areaSize : EditText
    lateinit var textShade : EditText
    lateinit var radioGroupLand : RadioGroup
    lateinit var Dry: RadioButton
    lateinit var Wet: RadioButton
    lateinit var Average: RadioButton
    lateinit var radioGroupDrought : RadioGroup
    lateinit var Yes: RadioButton
    lateinit var No: RadioButton
    lateinit var SaveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_acivity)

        areaLocation = findViewById(R.id.areaLocation)
        areaSize = findViewById(R.id.areaSize)
        radioGroupLand = findViewById(R.id.radioGroupLand)
        Dry = findViewById(R.id.Dry)
        Wet = findViewById(R.id.Wet)
        Average = findViewById(R.id.Average)
        radioGroupDrought = findViewById(R.id.radioGroupDrought)
        Yes = findViewById(R.id.Yes)
        No = findViewById(R.id.No)
        textShade = findViewById(R.id.editTextShade)
        SaveButton = findViewById(R.id.SavButton)


        SaveButton.setOnClickListener {

            saveSurvey()
            startActivity(Intent(this, SurveySecondActivity::class.java))
        }
    }

    private fun saveSurvey(){

        val location = areaLocation.text.toString().trim()
        val size = areaSize.text.toString().toInt()
        val dry = Dry.text.toString().trim()
        val wet = Wet.text.toString().trim()
        val average = Average.text.toString().trim()
        val yes = Yes.text.toString().trim()
        val no = No.text.toString().trim()
        val shade = textShade.toString().trim()

        if(location.isEmpty()){

            areaLocation.error = "Please enter location"
            return
        }
        if(shade.isEmpty()){

            textShade.error = "Please enter shade type"
            return
        }

        val myDataBase = FirebaseDatabase.getInstance().getReference("Survey")
        val surveyId = myDataBase.push().key
        val survey = surveyId?.let { Survey(it, location, size, dry, wet, average, yes, no, shade) }

        if (surveyId != null) {
            myDataBase.child(surveyId).setValue(survey).addOnCompleteListener{

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

}