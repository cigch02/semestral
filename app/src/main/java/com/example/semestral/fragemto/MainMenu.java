package com.example.semestral.fragemto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.semestral.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenu extends Fragment {

    View v;
    Button Bo,Bg,Bl;
    Intent i;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
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
        v =  inflater.inflate(R.layout.fragment_main_menu, container, false);
        Bl = (Button) v.findViewById(R.id.Luz);
        Bo = (Button) v.findViewById(R.id.Orientación);
        Bg = (Button) v.findViewById(R.id.Gravedad);

        Bl.setOnClickListener(view ->  {
            i = new Intent(getActivity(), luz_activity.class);
            startActivity(i);

        });

        Bo.setOnClickListener(view -> {
            i = new Intent(getActivity(), Orientacion.class);
            startActivity(i);

        });

        Bg.setOnClickListener(view -> {
            i = new Intent(getActivity(), gravedad.class);
            startActivity(i);
        });

        // Inflate the layout for this fragment

        return v;
    }
}