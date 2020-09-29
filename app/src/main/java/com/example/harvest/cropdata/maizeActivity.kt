package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_maize.*

class maizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maize)

        infoMore3.setOnClickListener {

            webview_player_view3.settings.javaScriptEnabled = true

            webview_player_view3.loadUrl("https://www.youtube.com/watch?v=ZglIV6e4jFY")
        }
    }
}