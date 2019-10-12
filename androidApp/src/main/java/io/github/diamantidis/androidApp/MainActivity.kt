package io.github.diamantidis.androidApp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import sample.Sample
import sample.hello

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sample().checkMe()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.main_text).text = hello()
    }
}