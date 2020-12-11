package com.example.bloquiapp;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.util.ArrayList;

public class AccesibilityService extends AccessibilityService {
    private static final long START_TIME_IN_MILLIS = 5000;
    private CountDownTimer countDownTimer;
    private boolean running= false;
    private long timeleft = START_TIME_IN_MILLIS;
    private ArrayList<String> actionsKeys;
    private final String FIXED_KEY = "ddud";
    private String currentKey = "";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        actionsKeys = new ArrayList<>();
        actionsKeys.add(codeTranfsormer(MainActivity.sharedPreferences.getString(MainActivity.TELEGRAM_TOGGLES,"")));
        actionsKeys.add(codeTranfsormer(MainActivity.sharedPreferences.getString(MainActivity.WHATSAPP_TOGGLES,"")));
        actionsKeys.add(codeTranfsormer(MainActivity.sharedPreferences.getString(MainActivity.SMS_TOGGLES,"")));
        actionsKeys.add(codeTranfsormer(MainActivity.sharedPreferences.getString(MainActivity.PHONECALL_TOGGLES,"")));


        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                if (timeleft<=100){
                    System.out.println("el timer paró en: "+timeleft);
                    stopTimer();
                    resetTimer();
                    currentKey = "";
                }
                if (running){
                    currentKey += "u";
                    System.out.println(currentKey);
                    for (int i = 0; i < actionsKeys.size(); i++) {
                        if (currentKey.equals(actionsKeys.get(i))) {
                            String contacto = "";
                            String mensaje = "";
                            switch (i){
                                case 0:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.TELEGRAM_CONTACT,"");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.TELEGRAM_MESSAGE,"");
                                    MyIntentService.startActionTLG(this,mensaje,contacto);
                                    break;
                                case 1:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.WHATSAPP_CONTACT,"");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.WHATSAPP_MESSAGE,"");
                                    MyIntentService.startActionWPP(this,mensaje,contacto);
                                    break;
                                case 2:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.SMS_CONTACT,"");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.SMS_MESSAGE,"");
                                    MyIntentService.startActionSMS(this,mensaje,contacto);
                                    break;
                                case 3:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.PHONECALL_CONTACT,"");
                                    MyIntentService.startActionCALL(this,contacto);
                                    break;
                                default:
                                    break;
                        }
                    }

                    }
                }else {
                    startTimer();
                    System.out.println("el timer empezó"+timeleft);
                    currentKey += "u";
                    System.out.println(currentKey);
                }
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                if (timeleft<=100){
                    System.out.println("el timer paró en: "+timeleft);
                    stopTimer();
                    resetTimer();
                    currentKey = "";
                }
                if (running){
                    currentKey += "d";
                    System.out.println(currentKey);
                    for (int i = 0; i < actionsKeys.size(); i++) {
                        if (currentKey.equals(actionsKeys.get(i))) {
                            String contacto = "";
                            String mensaje = "";
                            switch (i) {
                                case 0:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.TELEGRAM_CONTACT, "");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.TELEGRAM_MESSAGE, "");
                                    MyIntentService.startActionTLG(this, mensaje, contacto);
                                    break;
                                case 1:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.WHATSAPP_CONTACT, "");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.WHATSAPP_MESSAGE, "");
                                    MyIntentService.startActionWPP(this, mensaje, contacto);
                                    break;
                                case 2:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.SMS_CONTACT, "");
                                    mensaje = MainActivity.sharedPreferences.getString(MainActivity.SMS_MESSAGE, "");
                                    MyIntentService.startActionSMS(this, mensaje, contacto);
                                    break;
                                case 3:
                                    contacto = MainActivity.sharedPreferences.getString(MainActivity.PHONECALL_CONTACT, "");
                                    MyIntentService.startActionCALL(this, contacto);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }else {
                    startTimer();
                    currentKey += "d";
                    System.out.println(currentKey);
                }
            }
            return super.onKeyEvent(event);
        } else {
            return super.onKeyEvent(event);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeleft, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                running = false;
            }
        }.start();

        running = true;
    }
    private void resetTimer(){
        timeleft = START_TIME_IN_MILLIS;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        running = false;
    }

    public String codeTranfsormer(String inputKey){
        String outputKey = "";
        for (int i = 0; i < inputKey.length(); i++) {
            if ((inputKey.charAt(i) == 'u') && i%2==0){
                for (int j = 0; j < Integer.parseInt(String.valueOf(inputKey.charAt(i + 1))); j++) {
                    outputKey += "u";
                }
            }else if ((inputKey.charAt(i) == 'd') && i%2==0){
                for (int j = 0; j < Integer.parseInt(String.valueOf(inputKey.charAt(i + 1))); j++) {
                    outputKey += "d";
                }
            }
        }
        return outputKey;
    }

}
