package com.example.kadamm;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.IOException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import rmi.TestService;

import java.io.File;
import java.util.ArrayList;
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

import javax.xml.transform.Result;

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
    private String nombre, data, respuesta;
    Runtime runtime = Runtime.getRuntime();
    private int mInterval = 3000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private Conn con;
    public CallHandler callHandler;
    public Client client;
    public TestService testService;
    boolean start = false;
    boolean finish = false;
    boolean seguir = false;
    int tiempoEspera;
    public int nump = 0;
    int test = 0;
    int LAUNCH_SECOND_ACTIVITY = 1;
    String tiempo;


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
                callHandler = new CallHandler();
                client = new Client(ip_text.getText().toString(), 2324, callHandler);
                testService = (TestService) client.getGlobal(TestService.class);
                boolean nametest = testService.getName(nombre);
                if (!nametest) {
                    client.close();
                    changeColor(R.drawable.red);
                    Intent myintent = new Intent(getContext(), PopUser.class);
                    startActivity(myintent);
                } else {
                    changeColor(R.drawable.green);
                    while (!start) {
                        start = testService.isStarted();
                        Thread.sleep(500);
                    }
                    try{
                        while (!finish) {
                            seguir = false;
                            ArrayList<String> myArray = testService.GetAnswers(nump);
                            tiempoEspera = testService.getTime();
                            Log.e("Tamaño", String.valueOf(myArray.size()));
                        /*if(nump == 0){
                            Intent myintent2 = new Intent(getContext(), Esperando.class);
                            startActivityForResult(myintent2, LAUNCH_SECOND_ACTIVITY);
                        }*/

                            if (myArray.size() == 2) {
                                Intent myintent = new Intent(getContext(), Answ_2.class);
                                myintent.putExtra("tiempo", tiempoEspera);
                                myintent.putExtra("preguntas", myArray);
                                startActivityForResult(myintent, LAUNCH_SECOND_ACTIVITY);
                            } else if (myArray.size() == 3) {
                                Intent myintent = new Intent(getContext(), Answ_3.class);
                                myintent.putExtra("tiempo", tiempoEspera);
                                myintent.putExtra("preguntas", myArray);
                                startActivityForResult(myintent, LAUNCH_SECOND_ACTIVITY);
                            } else if (myArray.size() == 4) {
                                Intent myintent = new Intent(getContext(), Answ_4.class);
                                myintent.putExtra("tiempo", tiempoEspera);
                                myintent.putExtra("preguntas", myArray);
                                startActivityForResult(myintent, LAUNCH_SECOND_ACTIVITY);
                            }
                            while (!seguir) {
                                seguir = testService.getSeguir();
                                Thread.sleep(500);
                                if (seguir) {
                                    test = test + 1;
                                    testService.RespuestaJugador(respuesta , tiempo, nombre);
                                    Thread.sleep(500);
                                }
                            }
                            nump++;
                        }
                    } catch (Exception e) {
                        //
                    }
                    }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                changeColor(R.drawable.red);
            }
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                respuesta=data.getStringExtra("result");
                tiempo=data.getStringExtra("tiempo");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}