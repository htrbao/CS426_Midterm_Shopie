package com.example.shopie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogoFragment extends Fragment {
    ImageButton button;
    LogoBtnFragmentListener listener;

    public interface LogoBtnFragmentListener {
        void onClickLogoBtn();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.logo_icon, container, false);
        button = (ImageButton) v.findViewById(R.id.logoPieBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickLogoBtn();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LogoFragment.LogoBtnFragmentListener) {
            listener = (LogoFragment.LogoBtnFragmentListener) context;
        }
    }
}
