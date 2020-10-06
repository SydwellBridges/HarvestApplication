package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.harvest.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_survey_acivity.*

class SurveyActivity : AppCompatActivity() {

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

        // Button to save data to database and continue to the next form
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
        //val survey = Survey(surveyId, location, size, dry, wet, average, yes, no, shade) }

        myDataBase.child("id").setValue(surveyId)
        myDataBase.child("location").setValue(areaLocation)
        myDataBase.child("size").setValue(areaSize)
        //myDataBase.child("dry").setValue(Dry)
        //myDataBase.child("wet").setValue(Wet)
        //myDataBase.child("average").setValue(Average)
        //myDataBase.child("yes").setValue(Yes)
        //myDataBase.child("no").setValue(No)
        myDataBase.child("shade").setValue(textShade)

        // Radio group for the type of land
        radioGroupLand.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Dry)
            {
                Toast.makeText(this, Dry.text.toString(), Toast.LENGTH_SHORT).show()
                myDataBase.child("dry").setValue(Dry)

            }

            if(checkedId == R.id.Wet)
            {
                Toast.makeText(this, Wet.text.toString(), Toast.LENGTH_SHORT).show()
                myDataBase.child("wet").setValue(Wet)

            }

            if(checkedId == R.id.Average)
            {
                Toast.makeText(this, Average.text.toString(), Toast.LENGTH_SHORT).show()
                myDataBase.child("average").setValue(Average)
            }
        }

        // Radio group for the drought
        radioGroupDrought.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Yes)
            {
                Toast.makeText(this, Yes.text.toString(), Toast.LENGTH_SHORT).show()
                myDataBase.child("yes").setValue(Yes)

            }

            if(checkedId == R.id.No)
            {
                Toast.makeText(this, No.text.toString(), Toast.LENGTH_SHORT).show()
                myDataBase.child("no").setValue(No)
            }
        }


    }

}