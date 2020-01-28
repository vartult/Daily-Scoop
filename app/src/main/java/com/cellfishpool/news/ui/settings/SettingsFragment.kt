package com.cellfishpool.news.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.CheckBoxPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.cellfishpool.news.R
import com.cellfishpool.news.utils.Constants
import com.cellfishpool.news.utils.Constants.PREF_NAME
import timber.log.Timber

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_preference)

        bindPreference(findPreference<CheckBoxPreference>(getString(R.string.dark_key))!!)
        bindCountryPreference(findPreference<ListPreference>(Constants.COUNTRY_KEY)!!)
    }



    private fun bindPreference(preference: Preference) {
        preference.onPreferenceChangeListener = this

        onPreferenceChange(
            preference, preference.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(preference.key, false)
        )
    }

    private fun bindCountryPreference(preference: Preference) {
        preference.onPreferenceChangeListener = this

        onPreferenceChange(
            preference,
            preference.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(
                preference.key,
                "in"
            )
        )
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        Timber.d("Important")
        val value = newValue
        val editor = preference?.context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)!!
            .edit()
        if (value is Boolean) {
            editor.putBoolean(preference.key, value)
                .apply()
            if (value) {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        } else if (value is String) {
            editor.putString(Constants.COUNTRY_KEY,value)
                .apply()
        }




        Timber.d(value.toString())
        return true
    }

}