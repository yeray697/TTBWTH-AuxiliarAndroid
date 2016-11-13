package com.phile.yrj.takethebullfighterwiththehorns;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import java.util.prefs.Preferences;

/**
 * Created by yeray697 on 13/11/16.
 */

public class Settings_Activity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
