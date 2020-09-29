package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_watermelon.*

class watermelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watermelon)

        infoMore8.setOnClickListener {

            webview_player_view8.settings.javaScriptEnabled = true

            webview_player_view8.loadUrl("https://www.youtube.com/watch?v=X793Uy8qMcQ")
        }
    }
}