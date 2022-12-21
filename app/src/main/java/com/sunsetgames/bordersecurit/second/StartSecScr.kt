package com.sunsetgames.bordersecurit.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunsetgames.bordersecurit.databinding.ActivityStartSecScrBinding

class StartSecScr : AppCompatActivity() {
    private lateinit var skdkms : ActivityStartSecScrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skdkms = ActivityStartSecScrBinding.inflate(layoutInflater)
        setContentView(skdkms.root)
        clickevo()

    }

    private fun clickevo(){
        skdkms.button.setOnClickListener {
            startActivity(Intent(this,Second::class.java))

        }

        skdkms.button2.setOnClickListener {
            startActivity(Intent(this,WinnersAct::class.java))
        }
    }
}