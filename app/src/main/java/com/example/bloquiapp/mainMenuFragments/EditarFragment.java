package com.example.bloquiapp.mainMenuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloquiapp.AdaptersAndModels.CustomPagerAdapter;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;
import com.example.bloquiapp.ToggleSequences.Call123ToggleFragment;
import com.example.bloquiapp.ToggleSequences.PhoneCallToggleFragment;
import com.example.bloquiapp.ToggleSequences.PoliceToggleFragment;
import com.example.bloquiapp.ToggleSequences.SMSToggleFragment;
import com.example.bloquiapp.ToggleSequences.TelegramToggleFragment;
import com.example.bloquiapp.ToggleSequences.WhatsAppToggleFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarFragment extends Fragment {

    private Button toMisAcciones, toNuevo;
    private ViewPager2 viewPager;
    private CustomPagerAdapter myAdapter;
    private FloatingActionButton flTelegram, flWhatsApp, flSMS, flPhoneCall, flPolice, fl123;


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

        viewPager = view.findViewById(R.id.editarViewPager);

        List<Fragment> list = new ArrayList<>();

        TelegramToggleFragment telTog = new TelegramToggleFragment();
        WhatsAppToggleFragment whatTog = new WhatsAppToggleFragment();
        SMSToggleFragment smsTog = new SMSToggleFragment();
        PoliceToggleFragment polTog = new PoliceToggleFragment();
        Call123ToggleFragment call123Tog = new Call123ToggleFragment();
        PhoneCallToggleFragment callTog = new PhoneCallToggleFragment();

        if (MainActivity.sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)){list.add(telTog); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)){ list.add(whatTog); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){ list.add(smsTog); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)){ list.add(polTog); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false)){ list.add(call123Tog); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){ list.add(callTog); }

        myAdapter = new CustomPagerAdapter(getActivity(), list);

        viewPager.setAdapter(myAdapter);

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

        return view;
    }


}