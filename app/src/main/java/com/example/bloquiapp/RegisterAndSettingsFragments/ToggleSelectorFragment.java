package com.example.bloquiapp.RegisterAndSettingsFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloquiapp.AdaptersAndModels.CustomPagerAdapter;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.MainMenu;
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
 * Use the {@link ToggleSelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToggleSelectorFragment extends Fragment {

    private ViewPager2 viewPager;
    private CustomPagerAdapter myAdapter;
    private Button btnTogSiguiente;
    private FloatingActionButton flTelegram, flWhatsApp, flSMS, flPhoneCall, flPolice, fl123;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToggleSelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToggleSelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToggleSelectorFragment newInstance(String param1, String param2) {
        ToggleSelectorFragment fragment = new ToggleSelectorFragment();
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
        View view = inflater.inflate(R.layout.fragment_toggle_selector, container, false);

        viewPager = view.findViewById(R.id.actionsViewPager);
        loadCards();

        btnTogSiguiente = view.findViewById(R.id.btnTogSiguiente);
        btnTogSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= MainActivity.getSettings().edit();
                editor.putBoolean("firstRun",true);
                editor.commit();
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void loadCards(){
        List<Fragment> list = new ArrayList<>();

        if (MainActivity.sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)){list.add(new TelegramToggleFragment()); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)){ list.add(new WhatsAppToggleFragment()); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){ list.add(new SMSToggleFragment()); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)){ list.add(new PoliceToggleFragment()); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false)){ list.add(new Call123ToggleFragment()); }
        if (MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){ list.add(new PhoneCallToggleFragment()); }

        myAdapter = new CustomPagerAdapter(getActivity(), list);

        viewPager.setAdapter(myAdapter);
    }

}