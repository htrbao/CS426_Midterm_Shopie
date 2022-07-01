package com.example.shopie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Information.Information;
import banner.BannerAdapter;
import me.relex.circleindicator.CircleIndicator2;
import menu.Menu;
import menu.MenuAdaper;
import menu.MenuListener;

public class MainActivity extends AppCompatActivity implements testFragment.TestFragmentListener{
    private testFragment testfragment;
    private MapsActivity mapsActivity;

    private bannerFragment bannerfragment;
    private menuFragment menuFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerfragment = new bannerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_banner, bannerfragment)
                .commit();

        menuFragment = new menuFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_list, menuFragment)
                .commit();

        testfragment = new testFragment();
        mapsActivity = new MapsActivity();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_test, testfragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerfragment.runAutoScrollBanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerfragment.stopAutoScrollBanner();
    }





    @Override
    public void onClickAboutUs() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}