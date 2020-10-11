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
import org.w3c.dom.Text

class SurveyAdapter(val mCtx : Context, private val layoutId : Int, private val surveyList : List<Survey>) : ArrayAdapter<Survey>(mCtx,layoutId,surveyList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(layoutId,null)

        val location = view.findViewById<TextView>(R.id.textViewArea)
        val size = view.findViewById<TextView>(R.id.textViewAreaSize)
        val land = view.findViewById<TextView>(R.id.textViewLandType)
        val slope = view.findViewById<TextView>(R.id.textViewSlopeType)
        val distanceRoad = view.findViewById<TextView>(R.id.textViewDistance)
        val shade =  view.findViewById<TextView>(R.id.textViewShade)

        val soiltype = view.findViewById<TextView>(R.id.textViewSoilType)
        val phlevel = view.findViewById<TextView>(R.id.textViewPHLevel)
        val soiltexture = view.findViewById<TextView>(R.id.textViewSoilTexture)
        val soilcolour = view.findViewById<TextView>(R.id.textViewSoilColour)
        val soilPorosity = view.findViewById<TextView>(R.id.textViewSoilPorosity)

        val updateBtn = view.findViewById<Button>(R.id.buttonUpdate)
        val deleteBtn = view.findViewById<Button>(R.id.buttonDelete)

        val survey = surveyList[position]

        location.text = "Location: " + survey.location
        size.text = "Area Size:" + survey.size.toString()
        land.text = "Land Type: " + survey.land
        slope.text = "Slope Type: " + survey.slope
        distanceRoad.text = "Distance from access: " + survey.distance.toString()
        shade.text = "Shade Type: "+ survey.shade

        soiltype.text = "Soil Type: " + survey.soiltype
        phlevel.text = "pH Level: " + survey.phlevel.toString()
        soiltexture.text = "Soil Texture:  " + survey.soiltexture
        soilcolour.text = "Soil Colour: " + survey.soilcolour
        soilPorosity.text = "Soil Porosity: " + survey.soilPorosity

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

        val soiltype = view.findViewById<EditText>(R.id.updateSoilType)
        val phlevel = view.findViewById<EditText>(R.id.updatePHLevel)
        val soiltexture = view.findViewById<EditText>(R.id.updateSoilTexture)
        val soilcolour = view.findViewById<EditText>(R.id.updateSoilColour)
        val soilPorosity = view.findViewById<EditText>(R.id.updateSoilPorosity)

        location.setText(survey.location)
        size.setText(survey.size.toString())
        land.setText(survey.land)
        slope.setText(survey.slope)
        distanceRoad.setText(survey.distance.toString())
        shade.setText(survey.shade)

        soiltype.setText(survey.soiltype)
        phlevel.setText(survey.phlevel.toString())
        soiltexture.setText(survey.soiltexture)
        soilcolour.setText(survey.soilcolour)
        soilPorosity.setText(survey.soilPorosity)

        builder.setView(view)

        builder.setPositiveButton("Update",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int){

                val  myDatabase = FirebaseDatabase.getInstance().getReference("Land Properties")

                val location1 = location.text.toString().trim()
                val size2 = size.text.toString().toInt()
                val land3 = land.text.toString().trim()
                val slope4 = slope.text.toString().trim()
                val distanceRoad5 = distanceRoad.text.toString().toInt()
                val shade6 = shade.text.toString().trim()

                val soiltype7 = soiltype.text.toString()
                val phlevel8 = phlevel.text.toString().toInt()
                val soiltexture9 = soiltexture.text.toString()
                val soilcolour10 = soilcolour.text.toString()
                val soilPorosity11 = soilPorosity.text.toString()

                if(location1.isEmpty()){
                    location.error = "Please enter location"
                    return
                }

                val survey = Survey(survey.id, location1, size2, land3, slope4, distanceRoad5, shade6, soiltype7, phlevel8, soiltexture9, soilcolour10, soilPorosity11)
                survey.id?.let { myDatabase.child(it).setValue(survey) }
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
        survey.id?.let { myDatabase.child(it).removeValue() }
        Toast.makeText(mCtx,"Deleted !",Toast.LENGTH_LONG).show()
    }

}