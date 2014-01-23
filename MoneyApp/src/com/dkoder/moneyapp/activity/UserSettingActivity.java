package com.dkoder.moneyapp.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.dkoder.moneyapp.R;

public class UserSettingActivity extends PreferenceActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.settings);
 
    }
	
}
