package com.example.harvest.navigate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_help.*
import me.relex.circleindicator.CircleIndicator3


class HelpActivity : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        postToList()

        view_pager2.adapter = ViewPagerAdapter(titleList, descList, imageList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)

    }

    private fun addToList(title: String, description: String, image: Int){
        titleList.add(title)
        descList.add(description)
        imageList.add(image)

    }

    private fun postToList(){

            addToList("Survey ", "Perform a simple survey on the land by completing the form that uses Artificial Intelligence to determine if the land and environment is in a good state for farming activity or gardening.", R.drawable.survey)
            addToList("Soil Testing", "This section provides you with all the information you need to perform a basic soil test that does not require a laboratory or soil expertise.", R.drawable.testing)
            addToList("Survey Results", "View your survey results and determine if you are about to make a worthy investment by growing crops on the land you have bought or chosen.", R.drawable.success)
            addToList("Crops Catalogue", "All the information you need to know to successfully plant the list common of crops provided. Know the type of soil the plamt require, its positioning against the sun, shade and wind and the correct way to harvest them once they are ripe.", R.drawable.crops)
            addToList("Weather ", "Know when it is proper to plant or water your crops with current and up to date weather forecast of your region.", R.drawable.weather)
    }
}