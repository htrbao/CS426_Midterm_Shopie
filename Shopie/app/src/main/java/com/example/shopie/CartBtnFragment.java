package com.example.shopie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CartBtnFragment extends Fragment {
    ImageButton button;
    CartBtnFragmentListener listener;

    public interface CartBtnFragmentListener {
        void onClickCartBtn();
    }

    public interface CartNoProduct {
        void changeEditText();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cart_icon, container, false);
        button = (ImageButton) v.findViewById(R.id.cartBtn);
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
}
