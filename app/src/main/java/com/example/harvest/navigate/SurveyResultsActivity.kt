package com.example.harvest.navigate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.harvest.R
import com.google.firebase.database.*

class SurveyResultsActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var surveyList : MutableList<Survey>
    lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_results)

        surveyList = mutableListOf()
        listview = findViewById(R.id.listview1)
        ref = FirebaseDatabase.getInstance().getReference("Land Properties")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                {
                    surveyList.clear()

                    for(e in p0.children)
                    {
                        val survey = e.getValue(Survey::class.java)
                        surveyList.add(survey!!)
                    }

                    val adapter = SurveyAdapter(this@SurveyResultsActivity, R.layout.survey, surveyList)
                    listview.adapter = adapter
                }
            }

        })


    }
}