package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements testFragment.TestFragmentListener{
    private testFragment testfragment;
    private MapsActivity mapsActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testfragment = new testFragment();
        mapsActivity = new MapsActivity();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_test, testfragment)
                .commit();
    }

    @Override
    public void onClickAboutUs() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}