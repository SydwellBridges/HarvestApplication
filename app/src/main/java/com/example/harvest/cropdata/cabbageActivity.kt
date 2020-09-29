package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_cabbage.*
import kotlinx.android.synthetic.main.activity_oranges.*

class cabbageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabbage)

        infoMore1.setOnClickListener {

            webview_player_view1.settings.javaScriptEnabled = true

            webview_player_view1.loadUrl("https://www.youtube.com/watch?v=Ux-DtELMW5A")

        }
    }
}