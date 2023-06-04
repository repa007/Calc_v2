package com.repa007.calc_v2

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import java.util.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Themes
        val themes = Themes(this)
        themes.applyDayNightOverride()
        setTheme(themes.getTheme())

    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            val appLanguagePreference = findPreference<Preference>("repa007.opencalculator.APP_LANGUAGE")

            // remove the app language button if you are using an Android version lower than v33 (Android 13)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                appLanguagePreference?.isVisible = false
            } else {
                // Display the current selected language
                appLanguagePreference?.summary = Locale.getDefault().displayLanguage
            }
            // Select app language button
            appLanguagePreference?.setOnPreferenceClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    launchChangeAppLanguageIntent()
                }
                true
            }
        }

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        fun launchChangeAppLanguageIntent() {
            try {
                Intent(Settings.ACTION_APP_LOCALE_SETTINGS).apply {
                    data = Uri.fromParts("package", requireContext().packageName, null)
                    startActivity(this)
                }
            } catch (e: Exception) {
                try {
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", requireContext().packageName, null)
                        startActivity(this)
                    }
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }
}