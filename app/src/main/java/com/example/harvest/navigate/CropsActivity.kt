package com.example.harvest.navigate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.harvest.R
import com.example.harvest.cropdata.*

class CropsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crops)

        val cabbage = findViewById<CardView>(R.id.cabbageCardView)
        val maize = findViewById<CardView>(R.id.maizeCardView)
        val spinach = findViewById<CardView>(R.id.spinachCardView)
        val lettuce = findViewById<CardView>(R.id.lettuceCardView)
        val tomatoes = findViewById<CardView>(R.id.tomatoesCardView)
        val sugarcane = findViewById<CardView>(R.id.sugarcaneCardView)
        val watermelon = findViewById<CardView>(R.id.watermelonCardView)
        val oranges = findViewById<CardView>(R.id.orangesCardView)

        cabbage.setOnClickListener{

            startActivity(Intent(this, cabbageActivity::class.java))
        }

        maize.setOnClickListener{

            startActivity(Intent(this, maizeActivity::class.java))
        }

        spinach.setOnClickListener{

            startActivity(Intent(this, spinachActivity::class.java))
        }

        lettuce.setOnClickListener{

            startActivity(Intent(this, lettuceActivity::class.java))
        }

        tomatoes.setOnClickListener{

            startActivity(Intent(this, tomatoesActivity::class.java))
        }

        sugarcane.setOnClickListener{

            startActivity(Intent(this, sugarcaneActivity::class.java))
        }

        watermelon.setOnClickListener{

            startActivity(Intent(this, watermelonActivity::class.java))
        }

        oranges.setOnClickListener{

            startActivity(Intent(this, orangesActivity::class.java))
        }
    }
}