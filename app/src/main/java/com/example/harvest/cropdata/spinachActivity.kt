package com.example.harvest.cropdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harvest.R
import kotlinx.android.synthetic.main.activity_spinach.*

class spinachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinach)

        infoMore5.setOnClickListener {

            webview_player_view5.settings.javaScriptEnabled = true


            webview_player_view5.loadUrl("https://www.youtube.com/watch?v=NINsEK5--0o")
        }
    }
}