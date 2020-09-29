package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_cabbage.*
import kotlinx.android.synthetic.main.activity_lettuce.*

class lettuceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lettuce)

        infoMore2.setOnClickListener {

            webview_player_view2.settings.javaScriptEnabled = true

            webview_player_view2.loadUrl("https://www.youtube.com/watch?v=hZs5IQ7vWZM")
        }
    }
}