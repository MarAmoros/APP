package com.example.kadamm;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String nombre, data;
    public EditText editText;

    public FragmentUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUser newInstance(String param1, String param2) {
        FragmentUser fragment = new FragmentUser();
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

    public void Nombre(View view) {
        try {
            File file = new File(getContext().getFilesDir(), "mydir");
            File Nick = new File(file, "nickname");
            Scanner myReader = new Scanner(Nick);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                nombre = data;
            }
            editText = (EditText) view.findViewById(R.id.editTextTextPersonName);
            editText.setText(nombre);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void createtxt(String text) {
        File dir = new File(getContext().getFilesDir(), "mydir");
        int duration = Toast.LENGTH_SHORT;
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            String nickname = "nickname";
            File gpxfile = new File(dir, nickname);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(text);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int duration = Toast.LENGTH_SHORT;
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        Nombre(view);
        final Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            EditText nick = view.findViewById(R.id.editTextTextPersonName);
            String text = nick.getText().toString();
            Toast.makeText(getContext(), "El numero que has posat es massa gran", duration).show();
            createtxt(text);
        });
        return view;
    }
}