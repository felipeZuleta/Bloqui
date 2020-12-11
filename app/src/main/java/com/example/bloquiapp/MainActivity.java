package com.example.bloquiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApp;
    public static Context getContextOfApp(){
        return contextOfApp;
    }

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
    public static final String TELEGRAM_CONTACT = "telContact",WHATSAPP_CONTACT = "wppContact",SMS_CONTACT = "smsContact",PHONECALL_CONTACT = "phoneCallContact";
    public static final String TELEGRAM_MESSAGE = "telMessage",WHATSAPP_MESSAGE = "wppMessage",SMS_MESSAGE = "smsMessage";


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApp = getApplicationContext();
        settings = getSharedPreferences("prefs",0);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        Dexter.withContext(this)
                .withPermission(Manifest.permission.SEND_SMS)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
        Dexter.withContext(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
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