package com.kawler.effmobile.domain.utils

import android.content.SharedPreferences


class SharedPreferencesUtil(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val USER_CITY = "user_city"
        private const val FIRST_LAUNCH = "first_launch"
    }

    fun setUserCity(city: String) {
        if (city.isNotEmpty() || city.trim() != "Куда угодно")
            sharedPreferences.edit().putString(USER_CITY, city.trim()).apply()
    }

    fun setFirstLaunch() {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, false).apply()
    }

    fun getFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    }

    fun getUserCity(): String? {
        return sharedPreferences.getString(USER_CITY, "Минск")
    }

    fun clearUserCity() {
        sharedPreferences.edit().remove(USER_CITY).apply()
    }
}
