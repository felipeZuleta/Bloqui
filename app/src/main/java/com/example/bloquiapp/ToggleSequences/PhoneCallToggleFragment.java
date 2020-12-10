package com.example.bloquiapp.ToggleSequences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloquiapp.MainActivity;
import com.example.bloquiapp.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class PhoneCallToggleFragment extends Fragment {
    private FloatingActionButton btnToAddVolUp, btnToAddVolDown;
    private LinearLayout placinToggles;
    private Button guardar, cargar;
    private String togglesSequence = "";
    private boolean viewLoaded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toggle_action_phonecall,container,false);

        String togglesConfigId = MainActivity.sharedPreferences.getString(MainActivity.PHONECALL_TOGGLES,"");
        cargar = view.findViewById(R.id.btnCallLoad);
        placinToggles = view.findViewById(R.id.placing_phone_call_toggles);
        btnToAddVolDown = view.findViewById(R.id.phoneCallVolDowm);
        btnToAddVolUp = view.findViewById(R.id.phoneCallVolUp);
        guardar = view.findViewById(R.id.CallGuardar);
        if (!togglesConfigId.equals("") && !viewLoaded){
            cargar.setVisibility(View.VISIBLE);
            cargar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < togglesConfigId.length(); i++) {
                        View loadtoggleView = inflater.inflate(R.layout.fragment_toggles, container, false);
                        System.out.println(togglesConfigId.charAt(i));
                        System.out.println(i%2);
                        if ((togglesConfigId.charAt(i) == 'u') && (i%2 == 0)){
                            TextView title= loadtoggleView.findViewById(R.id.toggles_title);
                            title.setText(getString(R.string.boton_volumen_up));
                            togglesSequence = togglesSequence+"u";
                            placinToggles.addView(loadtoggleView);
                            Button lessTimesBtn = loadtoggleView.findViewById(R.id.default_less_button);
                            Button addTimesBtn = loadtoggleView.findViewById(R.id.default_plus_button);
                            String newTag = "counter"+togglesSequence.length();
                            TextView indicator = loadtoggleView.findViewById(R.id.default_times_counter);
                            indicator.setText(String.valueOf(togglesConfigId.charAt(i+1)));
                            indicator.setTag(newTag);
                            lessTimesBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Integer.parseInt((String)indicator.getText())>1) {
                                        indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) - 1));
                                    }
                                }
                            });
                            addTimesBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Integer.parseInt((String)indicator.getText())<8) {
                                        indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) + 1));
                                    }
                                }
                            });
                        }else if((togglesConfigId.charAt(i) == 'd') && (i%2 == 0)){
                            placinToggles.addView(loadtoggleView);
                            togglesSequence = togglesSequence+"d";
                            Button lessTimesBtn = loadtoggleView.findViewById(R.id.default_less_button);
                            Button addTimesBtn = loadtoggleView.findViewById(R.id.default_plus_button);
                            String newTag = "counter"+togglesSequence.length();
                            TextView indicator = loadtoggleView.findViewById(R.id.default_times_counter);
                            indicator.setText(String.valueOf(togglesConfigId.charAt(i+1)));
                            indicator.setTag(newTag);
                            lessTimesBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Integer.parseInt((String)indicator.getText())>1) {
                                        indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) - 1));
                                    }
                                }
                            });
                            addTimesBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Integer.parseInt((String)indicator.getText())<8) {
                                        indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) + 1));
                                    }
                                }
                            });
                        }
                    }
                    cargar.setVisibility(View.GONE);
                    viewLoaded = true;
                }
            });
        }
        btnToAddVolDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View toggleView = inflater.inflate(R.layout.fragment_toggles, container, false);
                placinToggles.addView(toggleView);
                togglesSequence = togglesSequence + "d";
                Button lessTimesBtn = toggleView.findViewById(R.id.default_less_button);
                Button addTimesBtn = toggleView.findViewById(R.id.default_plus_button);
                String newTag = "counter" + togglesSequence.length();
                TextView indicator = toggleView.findViewById(R.id.default_times_counter);
                indicator.setTag(newTag);
                lessTimesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt((String) indicator.getText()) > 1) {
                            indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) - 1));
                        }
                    }
                });
                addTimesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt((String) indicator.getText()) < 8) {
                            indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) + 1));
                        }
                    }
                });
                System.out.println(togglesSequence);
                System.out.println(indicator.getTag());
            }
        });

        btnToAddVolUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View toggleView = inflater.inflate(R.layout.fragment_toggles, container, false);
                TextView title = toggleView.findViewById(R.id.toggles_title);
                title.setText(getString(R.string.boton_volumen_up));
                togglesSequence = togglesSequence + "u";
                placinToggles.addView(toggleView);
                Button lessTimesBtn = toggleView.findViewById(R.id.default_less_button);
                Button addTimesBtn = toggleView.findViewById(R.id.default_plus_button);
                String newTag = "counter" + togglesSequence.length();
                TextView indicator = toggleView.findViewById(R.id.default_times_counter);
                indicator.setTag(newTag);
                lessTimesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt((String) indicator.getText()) > 1) {
                            indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) - 1));
                        }
                    }
                });
                addTimesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt((String) indicator.getText()) < 8) {
                            indicator.setText(String.valueOf(Integer.parseInt((String) indicator.getText()) + 1));
                        }
                    }
                });
                System.out.println(togglesSequence);
                System.out.println(indicator.getTag());
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder togglesIdBuilder = new StringBuilder();
                for (int i = 1; i <= togglesSequence.length(); i++) {
                    String tvTag = "counter" + i;
                    TextView tv = view.findViewWithTag(tvTag);
                    togglesIdBuilder.append(togglesSequence.charAt(i - 1)).append(tv.getText().toString());
                }
                MainActivity.editor.putString(MainActivity.PHONECALL_TOGGLES, String.valueOf(togglesIdBuilder));
                MainActivity.editor.apply();
                viewLoaded = false;
            }
        });

        return view;
    }

}

