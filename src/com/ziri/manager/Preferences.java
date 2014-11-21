package com.ziri.manager;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.zirilearndz.R;

public class Preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}