package com.huzaifa.designpatterndemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    var lines = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t = Thread(Runnable {
            try {
                val url = URL(
                    "http://api.openweathermap.org/data/2.5/weather?id=1172451&APPID=2b2996ddc0b0d9cf80e3b03a46e0c146"
                )
                val data = url.openStream()
                val br = BufferedReader(InputStreamReader(data))
                for (line in br.readLines()){
                    lines += line
                    Log.d("Data", lines)
                }
                runOnUiThread{
                    textview.text = lines
                }
            } catch(e : Exception) {
                e.printStackTrace()
            }
        })
        t.start()
    }
}
