package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;

import product.Product;

public class DescriptionActivity extends AppCompatActivity {
    private descriptionFragment descriptionFragment;
    private CartBtnFragment cartBtnFragment;
    private LogoFragment logoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        cartBtnFragment = new CartBtnFragment();
        logoFragment = new LogoFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_logo, logoFragment)
                .replace(R.id.container_cart, cartBtnFragment)
                .commit();

        if (getIntent().getExtras() != null) {
            Product product = (Product) getIntent().getExtras().get("detail_product");
            descriptionFragment = new descriptionFragment(product);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container, descriptionFragment)
                    .commit();
        }
    }
}