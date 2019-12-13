package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val name = intent.getStringExtra(EXTRA_MESSAGE_NAME)
        val game = intent.getStringExtra(EXTRA_MESSAGE_GAME)

        findViewById<TextView>(R.id.textView1).apply { text = name }
        findViewById<TextView>(R.id.textView2).apply { text = game }
    }
}
