package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import menu.Menu;
import menu.MenuAdaper;

public class MainActivity extends AppCompatActivity implements testFragment.TestFragmentListener{
    private testFragment testfragment;
    private MapsActivity mapsActivity;
    private RecyclerView menuRCV;
    private MenuAdaper menuAdaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuRCV = findViewById(R.id.menuRCV);
        menuAdaper = new MenuAdaper(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        menuRCV.setLayoutManager(linearLayoutManager);
        menuAdaper.setData(getListMenu());
        menuRCV.setAdapter(menuAdaper);

        testfragment = new testFragment();
        mapsActivity = new MapsActivity();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_test, testfragment)
                .commit();
    }

    private List<Menu> getListMenu() {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu(R.drawable.example1, "Menu 1"));
        list.add(new Menu(R.drawable.example1, "Menu 2"));
        list.add(new Menu(R.drawable.example1, "Menu 3"));
        list.add(new Menu(R.drawable.example1, "Menu 4"));
        list.add(new Menu(R.drawable.example1, "Menu 5"));
        list.add(new Menu(R.drawable.example1, "Menu 6"));
        list.add(new Menu(R.drawable.example1, "Menu 7"));
        list.add(new Menu(R.drawable.example1, "Menu 8"));
        list.add(new Menu(R.drawable.example1, "Menu 9"));
        list.add(new Menu(R.drawable.example1, "Menu 10"));
        return list;
    }

    @Override
    public void onClickAboutUs() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}