package com.example.bloquiapp.mainMenuFragments;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloquiapp.AdaptersAndModels.ViewPageAdapter;
import com.example.bloquiapp.Logic.Usuario;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;
import com.example.bloquiapp.RegisterAndSettingsFragments.ActionsSelectorFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisAccionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisAccionesFragment extends Fragment {

    private LinearLayout placingActions;
    private Button toNuevo, toEditar;
    private SharedPreferences sharedPreferences = MainActivity.sharedPreferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MisAccionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisAccionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisAccionesFragment newInstance(String param1, String param2) {
        MisAccionesFragment fragment = new MisAccionesFragment();
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

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_acciones, container, false);

        placingActions = view.findViewById(R.id.placing_actions_in_mis_acciones);

        toEditar = view.findViewById(R.id.btnMisAccionesToEditar);
        toEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_misAccionesFragment_to_editarFragment);
            }
        });

        toNuevo = view.findViewById(R.id.btnMisAccionesToNuevo);
        toNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_misAccionesFragment_to_nuevoFragment);
            }
        });

        if (sharedPreferences.getBoolean(MainActivity.TELEGRAM_SWITCH,false)) {
            View telView = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView telTitulo = telView.findViewById(R.id.action_in_main_title);
            TextView telContacto = telView.findViewById(R.id.actions_in_main_contacto);
            ImageView telIcon = telView.findViewById(R.id.action_in_main_icon);

            telTitulo.setText(getString(R.string.mensaje_de_emergencia));
            telIcon.setImageResource(R.drawable.telegram_logo);
            telContacto.setBackground(getResources().getDrawable(R.drawable.round_outline_blue));

            placingActions.addView(telView);
        }
        if (sharedPreferences.getBoolean(MainActivity.WHATSAPP_SWITCH,false)) {
            View wppView = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView wppTitulo = wppView.findViewById(R.id.action_in_main_title);
            TextView wppContacto = wppView.findViewById(R.id.actions_in_main_contacto);
            ImageView wppIcon = wppView.findViewById(R.id.action_in_main_icon);

            wppTitulo.setText(getString(R.string.mensaje_de_emergencia));
            wppIcon.setImageResource(R.drawable.whatsapp_logo);
            wppContacto.setBackground(getResources().getDrawable(R.drawable.round_outline_green));

            placingActions.addView(wppView);
        }
        if (sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)) {
            View smsView = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView smsTitulo = smsView.findViewById(R.id.action_in_main_title);
            TextView smsContacto = smsView.findViewById(R.id.actions_in_main_contacto);
            ImageView smsIcon = smsView.findViewById(R.id.action_in_main_icon);

            smsTitulo.setText(getString(R.string.mensaje_de_emergencia));
            smsIcon.setImageResource(R.drawable.sms_logo);
            smsContacto.setBackground(getResources().getDrawable(R.drawable.round_outline_yellow));

            placingActions.addView(smsView);
        }
        if (sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)) {
            View phView = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView phTitulo = phView.findViewById(R.id.action_in_main_title);
            TextView phContacto = phView.findViewById(R.id.actions_in_main_contacto);
            ImageView phIcon = phView.findViewById(R.id.action_in_main_icon);

            phTitulo.setText(getString(R.string.llamada_de_emergencia));
            phIcon.setImageResource(R.drawable.call_icon);
            phContacto.setBackground(getResources().getDrawable(R.drawable.round_outline_orange));

            placingActions.addView(phView);
        }
        if (sharedPreferences.getBoolean(MainActivity.POLICE_SWITCH,false)) {
            View polView = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView polTitulo = polView.findViewById(R.id.action_in_main_title);
            TextView polContacto = polView.findViewById(R.id.actions_in_main_contacto);
            ImageView polIcon = polView.findViewById(R.id.action_in_main_icon);

            String policetxt = getString(R.string.llamada_de_emergencia)+"(Policia)";
            polTitulo.setText(policetxt);
            polIcon.setImageResource(R.drawable.call_icon);
            polContacto.setBackground(getResources().getDrawable(R.drawable.round_outline_blue));

            placingActions.addView(polView);
        }
        if (sharedPreferences.getBoolean(MainActivity.P123_SWITCH,false)) {
            View ph123View = inflater.inflate(R.layout.actions_in_main,container,false);
            TextView ph123Titulo = ph123View.findViewById(R.id.action_in_main_title);
            TextView ph123Contacto = ph123View.findViewById(R.id.actions_in_main_contacto);
            ImageView ph123Icon = ph123View.findViewById(R.id.action_in_main_icon);

            String txt123 = getString(R.string.llamada_de_emergencia)+"(123)";
            ph123Titulo.setText(txt123);
            ph123Icon.setImageResource(R.drawable.call_icon);
            ph123Contacto.setBackground(getResources().getDrawable(R.drawable.round_outline_green));

            placingActions.addView(ph123View);
        }


        return view;
    }
}