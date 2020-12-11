package com.example.bloquiapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    private static final String ACTION_TLG = "com.example.bloquiapp.action.TLG";
    private static final String ACTION_WPP = "com.example.bloquiapp.action.BAZ";
    private static final String ACTION_SMS = "com.example.bloquiapp.action.SMS";
    private static final String ACTION_CALL = "com.example.bloquiapp.action.CALL";


    private static final String MESSAGE = "com.example.bloquiapp.extra.PARAM1";
    private static final String NUMBER = "com.example.bloquiapp.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }


    public static void startActionWPP(Context context, String message, String number) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_WPP);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(NUMBER, number);
        context.startService(intent);
    }

    public static void startActionSMS(Context context, String message, String number) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_SMS);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(NUMBER, number);
        context.startService(intent);
    }

    public static void startActionTLG(Context context, String message, String number) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_TLG);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(NUMBER, number);
        context.startService(intent);
    }

    public static void startActionCALL(Context context, String number) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_CALL);
        intent.putExtra(NUMBER, number);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String param1 = intent.getStringExtra(MESSAGE);
            final String param2 = intent.getStringExtra(NUMBER);
            final String action = intent.getAction();
            switch (action){
                case ACTION_SMS:
                    handleActionSMS(param1,param2);
                    break;
                case ACTION_WPP:
                    handleActionWPP(param1, param2);
                    break;
                case ACTION_TLG:
                    handleActionTLG(param1,param2);
                    break;
                case ACTION_CALL:
                    handleActionCALL(param2);
                    break;
                default:
                    break;
            }
        }
    }

    private void handleActionWPP(String message, String phoneNumber) {
        try {
            PackageManager packageManager = getApplicationContext().getPackageManager();
            String url = "https://api.whatsapp.com/send?phone="+phoneNumber + "&text="+message+getString(R.string.whatsapp_suffix);
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.setData(Uri.parse(url));
            whatsappIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if(whatsappIntent.resolveActivity(packageManager) != null){
                getApplicationContext().startActivity(whatsappIntent);
                Thread.sleep(10000);
            }else{
                System.out.println("wpp no sta");
            }
        }catch (Exception e){

        }
    }

    private void handleActionSMS(String message, String phoneNumber) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);
        }catch (Exception e){
            System.out.println("not sent");
        }
    }
    private void handleActionCALL(String phoneNumber) {
        String s = "tel:"+phoneNumber;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void handleActionTLG(String message, String phoneNumber) {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isPackageInstalled(appName,getPackageManager());
        if (isAppInstalled)
        {
            try {
                Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
                telegramIntent.setData(Uri.parse("http://telegram.me/"+phoneNumber));
                startActivity(telegramIntent);

            } catch (Exception e) {
                // show error message
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Telegram not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}