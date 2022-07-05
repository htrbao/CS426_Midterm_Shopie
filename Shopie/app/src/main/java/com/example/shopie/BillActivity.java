package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import product.Cart;
import product.CartProduct;
import product.CartProductAdapter;
import product.CartProductListener;

public class BillActivity extends AppCompatActivity {

    private CartProductAdapter cartProductAdapter;
    private RecyclerView billRecyclerView;
    TextView noti, total;
    Button shopieBtn, acCheckoutBtn;
    ImageView sadShoppingCart;
    LinearLayout layout;
    int totalI = 0;

    @Override
    protected void onResume() {
        super.onResume();
        if(Cart.cartProductList.size() == 0)
        {
            nothing();
        } else {
            totalI = 0;

            layout = (LinearLayout) findViewById(R.id.ln_checkout);
            layout.setVisibility(View.VISIBLE);

            for(int i = 0; i < Cart.cartProductList.size(); i++) {
                totalI += Cart.cartProductList.get(i).getPriceInInt() * Integer.valueOf(Cart.cartProductList.get(i).getQuantity());
            }
            total.setText(normalizePriceString(String.valueOf(totalI)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bill);

        getSupportActionBar().hide();

        billRecyclerView = findViewById(R.id.bill);

        total = (TextView) findViewById(R.id.total);

        cartProductAdapter = new CartProductAdapter(getApplication().getApplicationContext(), new CartProductListener() {
            @Override
            public void onClickIncBtn(int position) {
                Cart.cartProductList.get(position).incQuantity();
                totalI += Cart.cartProductList.get(position).getPriceInInt();
                System.out.println(totalI);
                total.setText(normalizePriceString(String.valueOf(totalI)));
            }

            @Override
            public void onClickDescBtn(int position) {
                if(Cart.cartProductList.isEmpty()) {
                    nothing();
                    return;
                }
                if(Cart.cartProductList.get(position) == null) return;
                totalI -= Cart.cartProductList.get(position).getPriceInInt();
                total.setText(normalizePriceString(String.valueOf(totalI)));
                Cart.cartProductList.get(position).descQuantity();
            }

            @Override
            public void onFocusQuanEdt(int position, String value) {
                if(Cart.cartProductList.isEmpty()) {
                    nothing();
                    return;
                }
                CartProduct cartProduct = Cart.cartProductList.get(position);
                if(cartProduct == null) return;
                int priceCartProduct = cartProduct.getPriceInInt();
                totalI -= priceCartProduct * Integer.valueOf(cartProduct.getQuantity());
                cartProduct.setQuantity(value);
                totalI += priceCartProduct * Integer.valueOf(cartProduct.getQuantity());
                total.setText(normalizePriceString(String.valueOf(totalI)));
            }
        });

        if(Cart.cartProductList.size() == 0)
        {
            nothing();
        } else {
            layout = (LinearLayout) findViewById(R.id.ln_checkout);
            layout.setVisibility(View.VISIBLE);

            for(int i = 0; i < Cart.cartProductList.size(); i++) {
                totalI += Cart.cartProductList.get(i).getPriceInInt() * Integer.valueOf(Cart.cartProductList.get(i).getQuantity());
            }
        }

        acCheckoutBtn = (Button) findViewById(R.id.acBillBtn);
        acCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, FormBillActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication().getApplicationContext());
        billRecyclerView.setLayoutManager(linearLayoutManager);
        billRecyclerView.setAdapter(cartProductAdapter);

    }

    public String normalizePriceString(String Price) {

        int length = Price.length();

        for(int i = length - 1; i >= 1; i--) {
            if((length - i) % 3 == 0)
                Price = new StringBuffer(Price).insert(i, ",").toString();
        }

        String tmp = Cart.cartProductList.get(0).getPrice();
        Price += tmp.charAt(tmp.length() - 1);

        return Price;
    }

    public void nothing() {
        totalI = 0;
        noti = (TextView) findViewById(R.id.notiInCart);
        shopieBtn = (Button) findViewById(R.id.shopieBtn);
        sadShoppingCart = (ImageView) findViewById(R.id.sad);
        layout = (LinearLayout) findViewById(R.id.ln_checkout);

        noti.setText("\"Nah\"thing in your cart");
        noti.setVisibility(View.VISIBLE);
        shopieBtn.setVisibility(View.VISIBLE);
        sadShoppingCart.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);
        shopieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}