package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_sugarcane.*

class sugarcaneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugarcane)

        infoMore6.setOnClickListener {

            webview_player_view6.settings.javaScriptEnabled = true


            webview_player_view6.loadUrl("https://www.youtube.com/watch?v=_lzWWpsdeSk")
        }
    }
}