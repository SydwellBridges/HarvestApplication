package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_oranges.*

class orangesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oranges)

        infoMore4.setOnClickListener {

            webview_player_view4.settings.javaScriptEnabled = true

            webview_player_view4.loadUrl("https://www.youtube.com/watch?v=n2nYr-Kf5rQ")

        }
    }
}