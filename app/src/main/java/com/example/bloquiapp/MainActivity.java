package com.example.bloquiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static boolean firstRun;

    public static void setSettings(SharedPreferences settings) {
        MainActivity.settings = settings;
    }

    public static SharedPreferences getSettings() {
        return settings;
    }

    protected static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences("prefs",0);
        firstRun = settings.getBoolean("firstRun",false);
        if(!firstRun)//if running for first time
        {

            Intent i=new Intent(MainActivity.this,StartActivity.class);//Activity to be     launched For the First time
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