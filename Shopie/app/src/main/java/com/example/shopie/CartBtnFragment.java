package com.example.shopie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import product.Cart;
import product.CartProduct;

public class CartBtnFragment extends Fragment {
    TextView textView;
    ImageButton button;
    CartBtnFragmentListener listener;

    public interface CartBtnFragmentListener {
        void onClickCartBtn();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cart_icon, container, false);
        textView = (TextView) v.findViewById(R.id.tv_productRunPls);
        button = (ImageButton) v.findViewById(R.id.cartBtn);
        //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(Cart.cartProductList.size()), Toast.LENGTH_SHORT).show();
        textView.setText(String.valueOf(Cart.cartProductList == null ? 0 : Cart.cartProductList.size()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCartBtn();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CartBtnFragment.CartBtnFragmentListener) {
            listener = (CartBtnFragment.CartBtnFragmentListener) context;
        }
    }

    public void changeEditText(String s) {
        textView.setText(s);
    }
}
