package com.example.bloquiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    protected static boolean firstRun;

    public static void setSettings(SharedPreferences settings) {
        MainActivity.settings = settings;
    }

    public static SharedPreferences getSettings() {
        return settings;
    }

    protected static SharedPreferences settings;

    public static final String SHARED_PREFS = "sharedPrefs", WHATSAPP_SWITCH = "whatsAppSwitch", TELEGRAM_SWITCH = "telegramSwitch", SMS_SWITCH = "smsSwitch", PHONE_SWITCH = "phoneSwitch", P123_SWITCH = "p123Switch", POLICE_SWITCH = "policeSwitch";
    public static final String WPP_AC = "whatsAppAlreadyConfigured",TEL_AC = "telegramAlreadyConfigured",SMS_AC = "smsAlreadyConfigured",PCON_AC = "contactCallAlreadyConfigured";
    public static final String TELEGRAM_TOGGLES = "telToggles",WHATSAPP_TOGGLES = "wppToggles",SMS_TOGGLES = "smsToggles",PHONECALL_TOGGLES = "phoneCallToggles",POLICE_TOGGLES = "policeToggles",CALL123_TOGGLES = "call123Toggles";


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences("prefs",0);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        firstRun = settings.getBoolean("firstRun",false);
        if(!firstRun)//if running for first time
        {

            Intent i=new Intent(MainActivity.this,StartActivity.class);//Activity to be launched For the First time
            startActivity(i);
        }
        else
        {
            Intent a=new Intent(MainActivity.this,MainMenu.class);//Default Activity
            startActivity(a);
        }
        finish();

    }


}