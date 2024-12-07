package com.kawler.effmobile.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.kawler.effmobile.R

class EffMobileAppIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(
            AppIntroFragment.createInstance(
                title = "Выполнены все основные требования из ТЗ",
                description = "В дополнение добавлен функционал с Room, если кол-во данных с Retrofit равно размеру таблиц в бд, то данные будут загружены из бд.",
                imageDrawable = R.mipmap.ic_launcher_round,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                titleTypefaceFontRes = R.font.sf_pro_semibold,
                descriptionTypefaceFontRes = R.font.sf_pro_medium,
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Приоритет Figma",
                description = "Если дизайны разнились в выборе шрифта и т.д предовалось предпочтение Figma.",
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                titleTypefaceFontRes = R.font.sf_pro_semibold,
                descriptionTypefaceFontRes = R.font.sf_pro_medium,
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Задание выполнялось с вечера вторника 03.12 до вечера субботы 07.12",
                description = "В соответствии с ТЗ",
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                titleTypefaceFontRes = R.font.sf_pro_semibold,
                descriptionTypefaceFontRes = R.font.sf_pro_medium,
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Используется R8 для релизной версии",
                description = "Рабочее сжатие приложения для релиза",
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                titleTypefaceFontRes = R.font.sf_pro_semibold,
                descriptionTypefaceFontRes = R.font.sf_pro_medium,
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}