package com.ptbc.kotlin_mvvm.ui.logo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ptbc.kotlin_mvvm.R
import com.ptbc.kotlin_mvvm.ui.splash.SplashActivity

class LogoActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        Handler().postDelayed({
            val mainIntent = Intent(this,SplashActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 1000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}