package com.example.harvest.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.harvest.R
import com.example.harvest.loginregister.Login
import com.example.harvest.navigate.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_menu_screen.*
import android.os.Bundle as Bundle1


class MainMenuScreen : AppCompatActivity() {
    
    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_screen)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = firebaseUser?.uid?.let {
            FirebaseDatabase.getInstance().reference.child("users").child(
                it
            )
        }


        val survey = findViewById<CardView>(R.id.surveyCardView)
        val performance = findViewById<CardView>(R.id.performanceCardView)
        val surveyResults = findViewById<CardView>(R.id.surveyResultsCardView)
        val cropsInfo = findViewById<CardView>(R.id.cropsInfoCardView)
        val weather = findViewById<CardView>(R.id.weatherCardView)
        val help = findViewById<CardView>(R.id.helpCardView)
        val logout = findViewById<CardView>(R.id.logoutCardView)

        // display username and profile image
        refUsers?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){

                    val user : User? = p0.getValue(User::class.java)
                    if (user != null) {
                        Picasso.get().load(user.profileImageUrl).placeholder(R.drawable.profile).into(profileImage)
                    }
                }
            }


        })

        // Access the Survey activity
        survey.setOnClickListener{

            startActivity(Intent(this, SurveyActivity::class.java))
        }

        // Access the Performance Activity
        performance.setOnClickListener{

            startActivity(Intent(this, SoiltestActivity::class.java))
        }

        // Access the Survey Results Activity
        surveyResults.setOnClickListener{

            startActivity(Intent(this, SurveyResultsActivity::class.java))
        }

        // Access the News and Info Activity
        cropsInfo.setOnClickListener{

            startActivity(Intent(this, CropsActivity::class.java))
        }

        // Access the Weather Activity
        weather.setOnClickListener{

            startActivity(Intent(this, WeatherActivity::class.java))
        }

        // Acess the Help Activity
        help.setOnClickListener{

            startActivity(Intent(this, HelpActivity::class.java))
        }

        // Logout
        logout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MainMenuScreen, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}