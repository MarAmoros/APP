package com.example.kadamm;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Answ_4 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answ_4);
        Button button = this.findViewById(R.id.button11);
        Button button2 = this.findViewById(R.id.button12);
        Button button3 = this.findViewById(R.id.button8);
        Button button4 = this.findViewById(R.id.button10);
        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        });
        button2.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        });
        button3.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        });
        button4.setOnClickListener(v -> {
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        });
    }
}
