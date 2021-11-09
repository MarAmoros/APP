package com.example.kadamm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentConn fragmentConn = new FragmentConn();
    FragmentUser fragmentUser = new FragmentUser();
    FragmentRanking fragmentRanking = new FragmentRanking();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(fragmentConn);
        changeImage();
        Button button_conn = findViewById(R.id.button_conn);
        try {
            changeImage();
            button_conn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        changeImage();
                    } catch (Exception E) {

                        Context context = getApplicationContext();
                        Toast.makeText(context, "No has introducido el numero", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Context context = getApplicationContext();
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeImage() {
        ImageView img= (ImageView) findViewById(R.id.trafic_light);
        img.setImageResource(R.drawable.yellow);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.fragment_conn:
                    loadFragment(fragmentConn);
                    return true;
                case R.id.fragment_user:
                    loadFragment(fragmentUser);
                    return true;
                case R.id.fragment_ranking:
                    loadFragment(fragmentRanking);
                    return true;
            }
            return false;
        }
    };


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace (R.id.frame_container, fragment);
        transaction.commit();
    }
}