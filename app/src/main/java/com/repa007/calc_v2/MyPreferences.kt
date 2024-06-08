package com.repa007.calc_v2

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.PreferenceManager
import com.google.gson.Gson

class MyPreferences(context: Context) {

    // https://proandroiddev.com/dark-mode-on-android-app-with-kotlin-dc759fc5f0e1
    companion object {
        private const val THEME = "repa007.calc_v2.THEME"
        private const val FORCE_DAY_NIGHT = "repa007.calc_v2.FORCE_DAY_NIGHT"
        private const val KEY_VIBRATION_STATUS = "repa007.calc_v2.KEY_VIBRATION_STATUS"
        private const val KEY_PREVENT_PHONE_FROM_SLEEPING = "repa007.calc_v2.PREVENT_PHONE_FROM_SLEEPING"
        private const val KEY_HISTORY_SIZE = "repa007.calc_v2.HISTORY_SIZE"
        private const val KEY_NUMBER_PRECISION = "repa007.calc_v2.NUMBER_PRECISION"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var theme = preferences.getInt(THEME, -1)
        set(value) = preferences.edit().putInt(THEME, value).apply()
    var forceDayNight = preferences.getInt(FORCE_DAY_NIGHT, MODE_NIGHT_UNSPECIFIED)
        set(value) = preferences.edit().putInt(FORCE_DAY_NIGHT, value).apply()

    var vibrationMode = preferences.getBoolean(KEY_VIBRATION_STATUS, true)
        set(value) = preferences.edit().putBoolean(KEY_VIBRATION_STATUS, value).apply()


    var preventPhoneFromSleepingMode = preferences.getBoolean(KEY_PREVENT_PHONE_FROM_SLEEPING, false)
        set(value) = preferences.edit().putBoolean(KEY_PREVENT_PHONE_FROM_SLEEPING, value).apply()
    var historySize = preferences.getString(KEY_HISTORY_SIZE, "100")
        set(value) = preferences.edit().putString(KEY_HISTORY_SIZE, value).apply()
    var numberPrecision = preferences.getString(KEY_NUMBER_PRECISION, "10")
        set(value) = preferences.edit().putString(KEY_NUMBER_PRECISION, value).apply()


}
