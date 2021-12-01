package com.example.kadamm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Answ_4 extends Activity {
    int tiempo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle preguntas = getIntent().getExtras();

        setContentView(R.layout.answ_4);
        Button button2 = this.findViewById(R.id.button11);
        Button button = this.findViewById(R.id.button12);
        Button button4 = this.findViewById(R.id.button8);
        Button button3 = this.findViewById(R.id.button10);
        TextView textView = this.findViewById(R.id.textView);

        ArrayList<String> values = preguntas.getStringArrayList("preguntas");
        tiempo = preguntas.getInt("tiempo");
        int cd = tiempo *1000;
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

        button.setText(values.get(0));
        button2.setText(values.get(1));
        button3.setText(values.get(2));
        button4.setText(values.get(3));

        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
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
            button4.setEnabled(false);
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
            button4.setEnabled(false);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",(String) button3.getText());
            returnIntent.putExtra("tiempo",String.valueOf(cd));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
        button4.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",(String) button4.getText());
            returnIntent.putExtra("tiempo",String.valueOf(cd));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }
}
