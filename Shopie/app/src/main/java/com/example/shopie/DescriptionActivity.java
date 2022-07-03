package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import product.Cart;
import product.Product;

public class DescriptionActivity extends AppCompatActivity implements descriptionFragment.addToCartListener{
    TextView textView;
    ImageButton button;

    private descriptionFragment descriptionFragment;
    private CartBtnFragment cartBtnFragment;
    private LogoFragment logoFragment;

    @Override
    protected void onResume() {
        super.onResume();
        cartBtnFragment.changeEditText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));
    }

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

        //textView = (TextView) findViewById(R.id.tv_productNo);
        //button = (ImageButton) findViewById(R.id.cartBtn);
        //textView.setText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));
        //cartBtnFragment.changeEditText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));

        if (getIntent().getExtras() != null) {
            Product product = (Product) getIntent().getExtras().get("detail_product");
            descriptionFragment = new descriptionFragment(product);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container, descriptionFragment)
                    .commit();
        }
    }

    @Override
    public void addProductListener(int n) {
        cartBtnFragment.changeEditText(String.valueOf(n));
    }
}