package com.example.kadamm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Answ_3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answ_3);
        Button button = this.findViewById(R.id.button3);
        Button button2 = this.findViewById(R.id.button7);
        Button button3 = this.findViewById(R.id.button5);
        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
        });
        button2.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
        });
        button3.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
        });
    }
}