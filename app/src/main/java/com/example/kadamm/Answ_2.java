package com.example.kadamm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Answ_2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answ_2);
        Button button = this.findViewById(R.id.button4);
        Button button2 = this.findViewById(R.id.button6);
        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
        });
        button2.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
        });
    }
}