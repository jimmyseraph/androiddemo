package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

const val EXTRA_MESSAGE_NAME = "com.example.myapplication.name"
const val EXTRA_MESSAGE_GAME = "com.example.myapplication.game"
var gameSelected = ""
val gameList = listOf("World of Warcraft", "StarCraft", "Diablo", "Might of Gate")

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gameList)
            .also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        spinner.onItemSelectedListener = SpinnerListener()
        WebView.setWebContentsDebuggingEnabled(true)
        val webView: WebView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webView.settings.loadWithOverviewMode = true
        webView.loadUrl("https://github.com/")
    }

    fun sendMessage(view: View) {
        val editText: EditText = findViewById(R.id.nameInput)
        val intent = Intent(this, DisplayMessageActivity::class.java).apply{
            putExtra(EXTRA_MESSAGE_NAME, editText.text.toString())
            putExtra(EXTRA_MESSAGE_GAME, gameSelected)
        }
        startActivity(intent)
    }

    class SpinnerListener : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
            gameSelected = ""
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            gameSelected = gameList[position]
        }


    }
}
