package com.kawler.effmobile.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kawler.effmobile.R
import com.kawler.effmobile.domain.utils.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        if (sharedPreferencesUtil.getFirstLaunch()) {
            sharedPreferencesUtil.setFirstLaunch()
            val intent = Intent(this, EffMobileAppIntro::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}