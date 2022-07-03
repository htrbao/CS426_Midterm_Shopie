package com.example.shopie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import product.Cart;
import product.Product;
import product.ProductListener;
import product.ShortProductAdapter;

public class ItemActivity extends AppCompatActivity {

    TextView textView;
    ImageButton button;

    private ShortProductAdapter shortProductAdapter;
    private CartBtnFragment cartBtnFragment;
    private LogoFragment logoFragment;
    private RecyclerView shortProductRecyclerView;
    private List<Product> list;
    private FirebaseDatabase database;
    private String category;

    @Override
    protected void onResume() {
        super.onResume();
        //textView.setText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));
        cartBtnFragment.changeEditText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().hide();

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

        list = new ArrayList<>();
        category = getIntent().getStringExtra("category");
        Toast.makeText(getApplicationContext(), category, Toast.LENGTH_SHORT).show();

        shortProductRecyclerView = findViewById(R.id.itemRCV);
        shortProductAdapter = new ShortProductAdapter(getApplication().getApplicationContext(), new ProductListener() {
            @Override
            public void onItemClicked(Product product) {
                Intent intent = new Intent(getApplication().getApplicationContext(), DescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detail_product", product);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication().getApplicationContext(), 2);

        shortProductRecyclerView.setLayoutManager(gridLayoutManager);
        shortProductAdapter.setData(list);
        shortProductRecyclerView.setAdapter(shortProductAdapter);
        database = FirebaseDatabase.getInstance();
        //Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        database.getReference().child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Product product = dataSnapshot1.getValue(Product.class);
                    if (product.getCategory().equals(category)) {
                        list.add(product);
                        //Toast.makeText(getApplicationContext(), product.getItems().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                shortProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getList() {
        //list.add(Product("Dot Grid", "Notebook", "xzxczxc", {"1","2","3"}))
    }
}