package com.example.kadamm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Answ_3 extends Activity {
    int tiempo;
    boolean seguir;
    int cd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle preguntas = getIntent().getExtras();
        setContentView(R.layout.answ_3);
        Button button = this.findViewById(R.id.button3);
        Button button2 = this.findViewById(R.id.button5);
        Button button3 = this.findViewById(R.id.button7);
        TextView textView = this.findViewById(R.id.textView);

        ArrayList<String> values = preguntas.getStringArrayList("preguntas");
        tiempo = preguntas.getInt("tiempo");

        button.setText(values.get(0));
        button2.setText(values.get(1));
        button3.setText(values.get(2));
        textView.setText(String.valueOf(tiempo));
         cd = tiempo *1000;
        new CountDownTimer(cd, 1000) {

            public void onTick(long millisUntilFinished) {
                tiempo = tiempo -1;
                textView.setText(String.valueOf(tiempo));
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();

        new CountDownTimer(cd, 1) {

            public void onTick(long millisUntilFinished) {
                cd = cd -1;
                textView.setText(String.valueOf(tiempo));
            }

            @Override
            public void onFinish() {

            }
        }.start();

        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",(String) button.getText());
            returnIntent.putExtra("tiempo",String.valueOf(cd));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
        button2.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",(String) button2.getText());
            returnIntent.putExtra("tiempo",String.valueOf(cd));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
        button3.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",(String) button3.getText());
            returnIntent.putExtra("tiempo",String.valueOf(cd));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }
}