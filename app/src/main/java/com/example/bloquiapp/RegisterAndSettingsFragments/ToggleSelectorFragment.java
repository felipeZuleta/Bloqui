package com.example.bloquiapp.RegisterAndSettingsFragments;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloquiapp.AdaptersAndModels.ActionsModel;
import com.example.bloquiapp.AdaptersAndModels.ViewPageAdapter;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToggleSelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToggleSelectorFragment extends Fragment {

    private ViewPager viewPager;
    private ArrayList<ActionsModel> actionsModelArrayList;
    private ViewPageAdapter myAdapter;
    private Button btnTogSiguiente;

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
            }
        });

        return view;
    }

    private void loadCards(){
        actionsModelArrayList = new ArrayList<>();

        if (ActionsSelectorFragment.isTelelgramSelected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.telegram_logo,R.color.appBlueT)); }
        if (ActionsSelectorFragment.isWhatsappSelected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.whatsapp_logo,R.color.appGreenT)); }
        if (ActionsSelectorFragment.isSMSselected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.mensaje_de_emergencia),R.drawable.sms_logo,R.color.appOrangeT)); }
        if (ActionsSelectorFragment.isPoliceSelected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (Policia)",R.drawable.call_icon,R.color.appBlueT)); }
        if (ActionsSelectorFragment.is123Selected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (123)",R.drawable.call_icon,R.color.appGreenT)); }
        if (ActionsSelectorFragment.isPhoneSelected){ actionsModelArrayList.add(new ActionsModel(getString(R.string.llamada_de_emergencia)+ " (Contacto)",R.drawable.call_icon,R.color.appYellowT)); }

        myAdapter = new ViewPageAdapter(getActivity(), actionsModelArrayList);

        viewPager.setAdapter(myAdapter);
    }
}