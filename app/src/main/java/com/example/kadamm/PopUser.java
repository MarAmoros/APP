package com.example.kadamm;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class PopUser extends Activity {
    private String nombre, data;
    public EditText editText;

    public PopUser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuser);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        final Button button = this.findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            EditText nick = this.findViewById(R.id.editTextTextPersonName2);
            String text = nick.getText().toString();
            if (text.replace(" ", "").equals("")){
                File dir = new File(this.getFilesDir(), "mydir");
                dir.delete();
            } else {
                createtxt(text);
            }        });
    }


/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int duration = Toast.LENGTH_SHORT;
        View view = inflater.inflate(R.layout.popuser, container, false);
        Nombre(view);
        final Button button = view.findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            EditText nick = view.findViewById(R.id.editTextTextPersonName2);
            String text = nick.getText().toString();
            createtxt(text);
        });
        return view;
    }*/

    public void createtxt(String text) {
        File dir = new File(this.getFilesDir(), "mydir");
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
    public void Nombre(View view) {
        try {
            File file = new File(this.getFilesDir(), "mydir");
            File Nick = new File(file, "nickname");
            Scanner myReader = new Scanner(Nick);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                nombre = data;
            }
            editText = (EditText) view.findViewById(R.id.editTextTextPersonName2);
            editText.setText(nombre);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
