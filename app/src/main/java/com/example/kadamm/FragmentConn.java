package com.example.kadamm;


import android.os.AsyncTask;
import java.io.IOException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

    private Button button_conn;
    private ImageView trafic_light;
    private EditText ip_text;

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
        View view  = inflater.inflate(R.layout.fragment_conn, container, false);
        // Inflate the layout for this fragment
        trafic_light = view.findViewById(R.id.trafic_light);
        ip_text = view.findViewById(R.id.ip_text);
        button_conn = view.findViewById(R.id.button_conn);

        button_conn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {


        new Conn().execute();
    }

    private void changeColor(int color) {

        trafic_light.setImageResource(color);
    }

    class Conn extends AsyncTask<Void, Void, FragmentConn> {

        @Override
        protected FragmentConn doInBackground(Void... params) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(ip_text.getText().toString(), 2324, callHandler);
                TestService testService = (TestService) client.getGlobal(TestService.class);
                String mesage = testService.getResponse("Correct connection");
                //Toast.makeText(MainActivity.this, testService.getResponse("abc"), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), mesage, Toast.LENGTH_LONG).show();
                changeColor(R.drawable.yellow);
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.loop();
            return null;
        }

    }


}