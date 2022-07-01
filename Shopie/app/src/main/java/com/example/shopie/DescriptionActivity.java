package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;

import Information.Information;

public class DescriptionActivity extends AppCompatActivity {
    private descriptionFragment descriptionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        if (getIntent().getExtras() != null) {
            Information info = (Information) getIntent().getExtras().get("info_item");
            descriptionFragment = new descriptionFragment(info);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container, descriptionFragment)
                    .commit();
        }
    }
}