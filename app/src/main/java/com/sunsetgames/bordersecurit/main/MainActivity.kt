package com.sunsetgames.bordersecurit.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.main.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewMainModel by viewModel<MainViewModel>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewMainModel.initAppsFlyerLib(this@MainActivity)

    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}
