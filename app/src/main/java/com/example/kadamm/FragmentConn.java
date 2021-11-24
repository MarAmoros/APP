package com.example.kadamm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.IOException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import java.io.File;
import java.util.Scanner;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentConn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConn extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button button_conn, b1, b2, b3;
    private ImageView trafic_light;
    private EditText ip_text, nickname;
    private View view;
    private String nombre, data;
    Runtime runtime = Runtime.getRuntime();
    private int mInterval = 3000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private Conn con;



    public FragmentConn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentConn.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentConn newInstance(String param1, String param2) {
        FragmentConn fragment = new FragmentConn();
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
        view  = inflater.inflate(R.layout.fragment_conn, container, false);
        // Inflate the layout for this fragment
        trafic_light = view.findViewById(R.id.trafic_light);
        ip_text = view.findViewById(R.id.ip_text);
        button_conn = view.findViewById(R.id.button_conn);
        button_conn.setOnClickListener(this);
        mHandler = new Handler();
        b1 = view.findViewById(R.id.button9);
        b2 = view.findViewById(R.id.button13);
        b3 = view.findViewById(R.id.button14);
        b1.setOnClickListener(v -> {
            Intent myintent = new Intent(getContext(), Answ_2.class);
            startActivity(myintent);
        });
        b2.setOnClickListener(v -> {
            Intent myintent = new Intent(getContext(), Answ_3.class);
            startActivity(myintent);
        });
        b3.setOnClickListener(v -> {
            Intent myintent = new Intent(getContext(), Answ_4.class);
            startActivity(myintent);
        });
        return view;
    }
    @Override
    public void onClick(View view) {
        changeColor(R.drawable.yellow);
        try {
            File file = new File(getContext().getFilesDir(), "mydir");
            File Nick = new File(file, "nickname");
            Scanner myReader = new Scanner(Nick);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                nombre = data;
            }
            myReader.close();
            if (con != null) {
                Log.e("1; ", "Ayudamematan") ;
                con.cancel(true);
            }
            con = new Conn();
            con.execute();
        } catch (Exception e) {
            e.printStackTrace();
            Intent myintent = new Intent(getContext(), PopUser.class);
            startActivity(myintent);
            // Error("No hay nombre");
        }
    }

    private void changeColor(int color) {

        trafic_light.setImageResource(color);
    }

    class Conn extends AsyncTask<Void, Void, FragmentConn> {

        @Override
        protected FragmentConn doInBackground(Void... params) {
            try {
                Log.e("", "aaaaaaa");
                CallHandler callHandler = new CallHandler();
                Client client = new Client(ip_text.getText().toString(), 2324, callHandler);
                TestService testService = (TestService) client.getGlobal(TestService.class);
                boolean nametest = testService.getName(nombre);
                if (!nametest) {
                    client.close();
                    changeColor(R.drawable.red);
                    Intent myintent = new Intent(getContext(), PopUser.class);
                    startActivity(myintent);
                    startRepeatingTask();
                } else {
                    changeColor(R.drawable.green);
                }
            } catch (IOException e) {
                e.printStackTrace();
                changeColor(R.drawable.red);
            }
            return null;
        }
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c " + ip_text.getText().toString());
                int mExitValue = mIpAddrProcess.waitFor();
                Log.e("1:", " mExitValue "+mExitValue);
                mHandler.postDelayed(mStatusChecker, mInterval);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Log.e("1:", "No va");
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
    //public void Error(String error) {
    //    changeColor(R.drawable.red);
    //
    //}
    /*public void Error(String error) {
        Looper.prepare();
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Error!");
        alert.setMessage(error);
        alert.setPositiveButton(
                "Ok!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (error == "Nombre repetido") {
                            Intent myintent = new Intent(getContext(), PopUser.class);
                            startActivity(myintent);
                        }
                        dialog.cancel();
                    }
                });
        alert.show();
        Looper.loop();
    }*/
}