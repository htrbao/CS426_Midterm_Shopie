package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;

import product.Product;

public class DescriptionActivity extends AppCompatActivity {
    private descriptionFragment descriptionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        if (getIntent().getExtras() != null) {
            Product product = (Product) getIntent().getExtras().get("detail_product");
            descriptionFragment = new descriptionFragment(product);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container, descriptionFragment)
                    .commit();
        }
    }
}