package com.example.shopie;

import android.content.Context;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
;
import java.util.ArrayList;
import java.util.List;

import product.Cart;
import product.CartProduct;
import product.Product;
import product.ProductListener;

public class descriptionFragment extends Fragment {

    Product product;

    public descriptionFragment(Product product) {
        this.product = product;
    }

    private addToCartListener listener;

    private ImageView img_description;
    private TextView name_description;
    private TextView price_description;
    private TextView brand_description;
    private TextView content_description;
    private Button  button;

    private AutoCompleteTextView autoCompleteTextView1;
    private ArrayAdapter<String> adapterItems1;

    private AutoCompleteTextView autoCompleteTextView2;
    private ArrayAdapter<String> adapterItems2;

    public interface addToCartListener {
        void addProductListener(int n);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_item, container, false);

        autoCompleteTextView1 = view.findViewById(R.id.auto_complete_txt1);
        adapterItems1 = new ArrayAdapter<String>(getContext(), R.layout.choice_list, product.getItems());
        autoCompleteTextView1.setAdapter(adapterItems1);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        autoCompleteTextView2 = view.findViewById(R.id.auto_complete_txt2);
        adapterItems2 = new ArrayAdapter<String>(getContext(), R.layout.choice_list, getQuatity());
        autoCompleteTextView2.setAdapter(adapterItems2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        img_description = view.findViewById(R.id.img_description);
        name_description = view.findViewById(R.id.name_description);
        price_description = view.findViewById(R.id.price_description);
        brand_description = view.findViewById(R.id.brand_description);
        content_description = view.findViewById(R.id.content_description);
        button = view.findViewById(R.id.add_cart_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = autoCompleteTextView2.getText().toString();
                String type = autoCompleteTextView1.getText().toString();
                CartProduct cartProduct = new CartProduct(product.getName(), product.getPrice(), product.getUrlProduct(), type, quantity);
                if (Cart.cartProductList == null)
                {
                    Cart.cartProductList = new ArrayList<>();
                }

                if (!quantity.isEmpty() && !type.isEmpty())
                {
                    Cart.cartProductList.add(cartProduct);
                    Toast.makeText(getActivity().getApplicationContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                    listener.addProductListener(Cart.cartProductList.size());
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Please choose quantity and type", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Glide.with(getContext()).load(product.getUrlProduct()).fitCenter().into(img_description);
        name_description.setText(product.getName());
        price_description.setText(product.getPrice());
        brand_description.setText(product.getBrand());
        content_description.setText(product.getDescription());

        return view;
    }

    private List<String> getQuatity()
    {
        List<String> quantity = new ArrayList<String>();;
        for (int i = 1; i <= 10; i++)
        {
            quantity.add(Integer.toString(i));
        }
        return quantity;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof descriptionFragment.addToCartListener) {
            listener = (descriptionFragment.addToCartListener) context;
        }
    }
}
