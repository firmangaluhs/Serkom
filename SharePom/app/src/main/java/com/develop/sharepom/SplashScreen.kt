package com.develop.sharepom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    val waktuloading: Int= 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val home = Intent(this@SplashScreen, BaseActivity::class.java)
            startActivity(home)
            finish()
        }, waktuloading.toLong())
    }
    }
