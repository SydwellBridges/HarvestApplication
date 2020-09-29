package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_tomatoes.*

class tomatoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomatoes)

        infoMore7.setOnClickListener {

            webview_player_view7.settings.javaScriptEnabled = true


            webview_player_view7.loadUrl("https://www.youtube.com/watch?v=9w-7RoH_uic")
        }
    }
}