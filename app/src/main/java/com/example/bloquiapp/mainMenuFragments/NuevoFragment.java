package com.example.bloquiapp.mainMenuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;
import com.example.bloquiapp.RegisterAndSettingsFragments.PhoneCallSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.SMSSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.TelegramSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.WhatsAppSetUpFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevoFragment extends Fragment {

    private Button toEditar, toMisAcciones, updateActions;
    private Switch telegramSwitch, whatsappSwitch, smsSwitch, phoneSwitch, call123switch, policeSwitch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NuevoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NuevoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NuevoFragment newInstance(String param1, String param2) {
        NuevoFragment fragment = new NuevoFragment();
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
        View view =  inflater.inflate(R.layout.fragment_nuevo, container, false);

        telegramSwitch = view.findViewById(R.id.telegramSwitchN);
        whatsappSwitch = view.findViewById(R.id.whatsAppSwitchN);
        smsSwitch = view.findViewById(R.id.smsSwitchN);
        phoneSwitch = view.findViewById(R.id.phoneSwitchN);
        call123switch = view.findViewById(R.id.call123switchN);
        policeSwitch = view.findViewById(R.id.callPoliceSwitchN);

        loadSwitches();

        toMisAcciones = view.findViewById(R.id.btnNuevoToMisAcciones);
        toMisAcciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_misAccionesFragment);
            }
        });

        toEditar = view.findViewById(R.id.btnNuevoToEditar);
        toEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_editarFragment);
            }
        });

        updateActions = view.findViewById(R.id.btnUpdate);
        updateActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSwitches();
                if(MainActivity.sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false) && (!MainActivity.sharedPreferences.getBoolean(MainActivity.TEL_AC,false))){
                    TelegramSetUpFragment.whereTelCameFrom = "nuevo";
                    Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_telegramSetUpFragment2);
                }else if(MainActivity.sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false) && (!MainActivity.sharedPreferences.getBoolean(MainActivity.WPP_AC,false))){
                    WhatsAppSetUpFragment.whereWppCameFrom = "nuevo";
                    Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_whatsAppSetUpFragment2);
                }else if(MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false) && (!MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_AC,false))){
                    SMSSetUpFragment.whereSMSCameFrom = "nuevo";
                    Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_SMSSetUpFragment2);
                }else if(MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false) && (!MainActivity.sharedPreferences.getBoolean(MainActivity.PCON_AC,false))){
                    PhoneCallSetUpFragment.wherePhoneCameFrom = "nuevo";
                    Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_phoneCallSetUpFragment2);
                }else if(MainActivity.sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false) || (MainActivity.sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false))){
                    Navigation.findNavController(view).navigate(R.id.action_nuevoFragment_to_misAccionesFragment);
                }
            }
        });

        return view;
    }

    public void loadSwitches(){
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)){
            whatsappSwitch.setChecked(true);
        }
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)){
            telegramSwitch.setChecked(true);
        }
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){
            smsSwitch.setChecked(true);
        }
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){
            phoneSwitch.setChecked(true);
        }
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)){
            policeSwitch.setChecked(true);
        }
        if(MainActivity.sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false)){
            call123switch.setChecked(true);
        }
    }

    public void updateSwitches(){
        MainActivity.editor.putBoolean(MainActivity.WHATSAPP_SWITCH, whatsappSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.TELEGRAM_SWITCH, telegramSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.SMS_SWITCH, smsSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.PHONE_SWITCH, phoneSwitch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.P123_SWITCH, call123switch.isChecked());
        MainActivity.editor.putBoolean(MainActivity.POLICE_SWITCH, policeSwitch.isChecked());

        if(!whatsappSwitch.isChecked() && MainActivity.sharedPreferences.getBoolean(MainActivity.WPP_AC,false)){
            MainActivity.editor.putBoolean(MainActivity.WPP_AC,false);
            MainActivity.editor.putString(MainActivity.WHATSAPP_TOGGLES,"");
        }
        if(!telegramSwitch.isChecked() && MainActivity.sharedPreferences.getBoolean(MainActivity.TEL_AC,false)){
            MainActivity.editor.putBoolean(MainActivity.TEL_AC,false);
            MainActivity.editor.putString(MainActivity.TELEGRAM_TOGGLES,"");
        }
        if(!smsSwitch.isChecked() && MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_AC,false)){
            MainActivity.editor.putBoolean(MainActivity.SMS_AC,false);
            MainActivity.editor.putString(MainActivity.SMS_TOGGLES,"");
        }
        if(!phoneSwitch.isChecked() && MainActivity.sharedPreferences.getBoolean(MainActivity.PCON_AC,false)){
            MainActivity.editor.putBoolean(MainActivity.PCON_AC,false);
            MainActivity.editor.putString(MainActivity.PHONECALL_TOGGLES,"");
        }
        if(!call123switch.isChecked()){
            MainActivity.editor.putString(MainActivity.CALL123_TOGGLES,"");
        }
        if (!policeSwitch.isChecked()){
            MainActivity.editor.putString(MainActivity.POLICE_TOGGLES,"");
        }
        MainActivity.editor.apply();
    }
}