package com.example.bloquiapp.RegisterAndSettingsFragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bloquiapp.Logic.Usuario;
import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;

import java.util.Currency;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhatsAppSetUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhatsAppSetUpFragment extends Fragment {

    private EditText edTxtContact;
    private Button btnSiguiente, btnSiguienteEditar, btnSiguienteNuevo;
    public static String whereWppCameFrom = "register";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WhatsAppSetUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhatsAppSetUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhatsAppSetUpFragment newInstance(String param1, String param2) {
        WhatsAppSetUpFragment fragment = new WhatsAppSetUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_whats_app_set_up, container, false);

        btnSiguiente = view.findViewById(R.id.btnWhaSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){
                    Navigation.findNavController(view).navigate(R.id.wha_to_sms);
                }else if (MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){
                    Navigation.findNavController(view).navigate(R.id.wha_to_phoneCall);
                }else {
                    Navigation.findNavController(view).navigate(R.id.wha_to_tog);
                }
            }
        });

        btnSiguienteEditar = view.findViewById(R.id.btnWhaSiguienteEditar);
        btnSiguienteEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_whatsAppSetUpFragment2_to_editarFragment);
            }
        });

        btnSiguienteNuevo = view.findViewById(R.id.btnWhaSiguienteNuevo);
        btnSiguienteNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.sharedPreferences.getBoolean(MainActivity.SMS_SWITCH,false)){
                    Navigation.findNavController(view).navigate(R.id.action_whatsAppSetUpFragment2_to_SMSSetUpFragment2);
                }else if (MainActivity.sharedPreferences.getBoolean(MainActivity.PHONE_SWITCH,false)){
                    Navigation.findNavController(view).navigate(R.id.action_whatsAppSetUpFragment2_to_phoneCallSetUpFragment2);
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_whatsAppSetUpFragment2_to_toggleSelectorFragment2);
                }
            }
        });

        edTxtContact = view.findViewById(R.id.edTxtContactSelector);
        edTxtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, 111);
            }
        });

        switch (whereWppCameFrom) {
            case "register":
                btnSiguiente.setVisibility(View.VISIBLE);
                btnSiguienteNuevo.setVisibility(View.GONE);
                btnSiguienteEditar.setVisibility(View.GONE);
                break;
            case "nuevo":
                btnSiguiente.setVisibility(View.GONE);
                btnSiguienteNuevo.setVisibility(View.VISIBLE);
                btnSiguienteEditar.setVisibility(View.GONE);
                break;
            case "editar":
                btnSiguiente.setVisibility(View.GONE);
                btnSiguienteNuevo.setVisibility(View.GONE);
                btnSiguienteEditar.setVisibility(View.VISIBLE);
                break;
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()){
                int indiceName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

                String nombre = cursor.getString(indiceName);

                edTxtContact.setText(nombre);
            }
        }
    }
}