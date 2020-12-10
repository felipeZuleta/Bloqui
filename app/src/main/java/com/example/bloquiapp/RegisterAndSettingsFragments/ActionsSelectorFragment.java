package com.example.bloquiapp.RegisterAndSettingsFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;


import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionsSelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionsSelectorFragment extends Fragment {

    private Button btnNext;
    private Switch telegramSwitch, whatsappSwitch, smsSwitch, phoneSwitch, call123switch, policeSwitch;
    private ImageView test;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActionsSelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionsSelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionsSelectorFragment newInstance(String param1, String param2) {
        ActionsSelectorFragment fragment = new ActionsSelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actions_selector, container, false);

        telegramSwitch = view.findViewById(R.id.telegramSwitch);
        whatsappSwitch = view.findViewById(R.id.whatsAppSwitch);
        smsSwitch = view.findViewById(R.id.smsSwitch);
        phoneSwitch = view.findViewById(R.id.phoneSwitch);
        call123switch = view.findViewById(R.id.call123switch);
        policeSwitch = view.findViewById(R.id.callPoliceSwitch);



        btnNext = view.findViewById(R.id.nextBtnAction);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                if (MainActivity.sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)) {
                    TelegramSetUpFragment.whereTelCameFrom = "register";
                    Navigation.findNavController(view).navigate(R.id.act_to_tel);
                } else if (MainActivity.sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)) {
                    WhatsAppSetUpFragment.whereWppCameFrom = "register";
                    Navigation.findNavController(view).navigate(R.id.act_to_wha);
                } else if (MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)) {
                    SMSSetUpFragment.whereSMSCameFrom = "register";
                    Navigation.findNavController(view).navigate(R.id.act_to_sms);
                } else if (MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)) {
                    PhoneCallSetUpFragment.wherePhoneCameFrom = "register";
                    Navigation.findNavController(view).navigate(R.id.act_to_phonecall);
                } else if (MainActivity.sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false) || MainActivity.sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)){
                    Navigation.findNavController(view).navigate(R.id.act_to_tog);
                }
            }
        });


        /*test = view.findViewById(R.id.logo2);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(isWhatsappSelected);
                System.out.println(isTelelgramSelected);
                System.out.println(isPhoneSelected);
                System.out.println(isSMSselected);
            }
        });*/

        return view;
    }

    public void saveData(){

        MainActivity.editor.putBoolean(MainActivity.WHATSAPP_SWITCH, whatsappSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.TELEGRAM_SWITCH, telegramSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.SMS_SWITCH, smsSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.PHONE_SWITCH, phoneSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.P123_SWITCH, call123switch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.POLICE_SWITCH, policeSwitch.isChecked());

        MainActivity.editor.apply();

    }

}