package com.example.bloquiapp.mainMenuFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloquiapp.AdaptersAndModels.ActionsModel;
import com.example.bloquiapp.AdaptersAndModels.ViewPageAdapter;
import com.example.bloquiapp.Logic.Usuario;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;
import com.example.bloquiapp.RegisterAndSettingsFragments.ActionsSelectorFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.PhoneCallSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.SMSSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.TelegramSetUpFragment;
import com.example.bloquiapp.RegisterAndSettingsFragments.WhatsAppSetUpFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarFragment extends Fragment {

    private Button toMisAcciones, toNuevo;
    private ViewPager viewPager;
    private ArrayList<ActionsModel> actionsModelArrayList;
    private ViewPageAdapter myAdapter;
    private SharedPreferences sharedPreferences = MainActivity.sharedPreferences;
    // Change Contact (cc)
    private Button ccTelegram, ccWhatsapp, ccSms, ccPhone;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarFragment newInstance(String param1, String param2) {
        EditarFragment fragment = new EditarFragment();
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
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        toMisAcciones = view.findViewById(R.id.btnEditarToMisAcciones);
        toMisAcciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_misAccionesFragment);
            }
        });

        toNuevo = view.findViewById(R.id.btnEditarToNuevo);
        toNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_nuevoFragment);
            }
        });
        if(view.findViewById(R.id.change_telegram_contact) != null) {
            ccTelegram = view.findViewById(R.id.change_telegram_contact);
            ccTelegram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TelegramSetUpFragment.whereTelCameFrom = "editar";
                    Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_telegramSetUpFragment2);
                }
            });
        }
        if(view.findViewById(R.id.change_whats_app_contact) != null) {
            ccWhatsapp = view.findViewById(R.id.change_whats_app_contact);
            ccWhatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WhatsAppSetUpFragment.whereWppCameFrom = "editar";
                    Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_whatsAppSetUpFragment2);
                }
            });
        }
        if(view.findViewById(R.id.change_sms_contact) != null) {
            ccSms = view.findViewById(R.id.change_sms_contact);
            ccSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SMSSetUpFragment.whereSMSCameFrom = "editar";
                    Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_SMSSetUpFragment2);
                }
            });
        }
        if(view.findViewById(R.id.change_phone_contact) != null) {
            ccPhone = view.findViewById(R.id.change_phone_contact);
            ccPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhoneCallSetUpFragment.wherePhoneCameFrom = "editar";
                    Navigation.findNavController(view).navigate(R.id.action_editarFragment_to_phoneCallSetUpFragment2);
                }
            });
        }

        viewPager = view.findViewById(R.id.editarViewPager);
        loadCards();

        return view;
    }

    private void loadCards(){
        actionsModelArrayList = new ArrayList<>();

        if (sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.telegram_logo,R.color.appBlueT,"telegram")); }
        if (sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.whatsapp_logo,R.color.appGreenT,"whatsapp")); }
        if (sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.sms_logo,R.color.appOrangeT,"sms")); }
        if (sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (Policia)",R.drawable.call_icon,R.color.appBlueT,"def")); }
        if (sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (123)",R.drawable.call_icon,R.color.appGreenT,"def")); }
        if (sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (Contacto)",R.drawable.call_icon,R.color.appYellowT,"phone")); }

        myAdapter = new ViewPageAdapter(getActivity(), actionsModelArrayList);

        viewPager.setAdapter(myAdapter);
    }
}