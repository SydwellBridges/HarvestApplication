package com.example.harvest.navigate

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.harvest.R
import com.google.firebase.database.FirebaseDatabase

class SurveyAdapter(val mCtx : Context, val layoutId : Int , val surveyList : List<Survey>) : ArrayAdapter<Survey>(mCtx,layoutId,surveyList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(layoutId,null)

        val location = view.findViewById<TextView>(R.id.textViewArea)
        val size = view.findViewById<TextView>(R.id.textViewAreaSize)
        val land = view.findViewById<TextView>(R.id.textViewLandType)
        val slope = view.findViewById<TextView>(R.id.textViewSlopeType)
        val distanceRoad = view.findViewById<TextView>(R.id.textViewDistance)
        val shade =  view.findViewById<TextView>(R.id.textViewShade)

        val updateBtn = view.findViewById<Button>(R.id.buttonUpdate)
        val deleteBtn = view.findViewById<Button>(R.id.buttonDelete)

        val survey = surveyList[position]

        location.text = survey.location
        size.text = survey.size.toString()
        land.text = survey.land
        slope.text = survey.slope
        distanceRoad.text = survey.distance
        shade.text = survey.shade

        updateBtn.setOnClickListener {
            updateInfo(survey)
        }

        deleteBtn.setOnClickListener{
            deleteInfo(survey)
        }

        return view
    }

    private fun updateInfo(survey : Survey)
    {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Info")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.survey_update,null)

        val location = view.findViewById<EditText>(R.id.locationUpdate)
        val size = view.findViewById<EditText>(R.id.areaSizeUpdate)
        val land = view.findViewById<EditText>(R.id.landTypeUpdate)
        val slope = view.findViewById<EditText>(R.id.slopeTypeUpdate)
        val distanceRoad = view.findViewById<EditText>(R.id.distanceUpdate)
        val shade =  view.findViewById<EditText>(R.id.shadeUpdate)

        location.setText(survey.location)
        size.setText(survey.size)
        land.setText(survey.land)
        slope.setText(survey.slope)
        distanceRoad.setText(survey.distance)
        shade.setText(survey.shade)

        builder.setView(view)

        builder.setPositiveButton("Update",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int){

                val  myDatabase = FirebaseDatabase.getInstance().getReference("Land Properties")

                val location1 = location.text.toString().trim()
                val size2 = size.text.toString().toInt()
                val land3 = land.text.toString().trim()
                val slope4 = slope.text.toString().trim()
                val distanceRoad5 = distanceRoad.toString().trim()
                val shade6 = shade.toString().trim()

                if(location1.isEmpty()){
                    location.error = "Please enter location"
                    return
                }

                val survey = Survey(survey.id, location1, size2, land3, slope4, distanceRoad5, shade6)
                myDatabase.child(survey.id).setValue(survey)
                    Toast.makeText(mCtx, "Updated", Toast.LENGTH_LONG).show()

            }
        })

        builder.setNegativeButton("cancel",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })

        val alert = builder.create()
        alert.show()

    }

    private fun deleteInfo(survey : Survey){
        val myDatabase = FirebaseDatabase.getInstance().getReference("Land Properties")
        myDatabase.child(survey.id).removeValue()
        Toast.makeText(mCtx,"Deleted !",Toast.LENGTH_LONG).show()
    }



}