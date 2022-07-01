package com.example.shopie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import banner.BannerAdapter;
import me.relex.circleindicator.CircleIndicator2;
import menu.Menu;
import menu.MenuAdaper;

public class MainActivity extends AppCompatActivity implements testFragment.TestFragmentListener{
    private testFragment testfragment;
    private MapsActivity mapsActivity;

    private RecyclerView bannerRCV;
    private BannerAdapter bannerAdapter;
    private CircleIndicator2 circleIndicator2;
    private LinearLayoutManager bannerLinearLayoutManager;
    private Timer timer;
    private TimerTask timerTask;
    private int position;

    private RecyclerView menuRCV;
    private MenuAdaper menuAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareRecyclerBanner();
        prepareRecyclerItem();

        testfragment = new testFragment();
        mapsActivity = new MapsActivity();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_test, testfragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }

    private void prepareRecyclerBanner() {
        List<String> urls = getListBanner();
        bannerRCV = findViewById(R.id.bannerRCV);
        bannerAdapter = new BannerAdapter(this, urls);
        bannerLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        bannerRCV.setLayoutManager(bannerLinearLayoutManager);
        bannerRCV.setAdapter(bannerAdapter);
        if (urls != null)
        {
            position = 0;
            bannerRCV.scrollToPosition(position);
        }
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(bannerRCV);
        bannerRCV.smoothScrollBy(5,0);
        circleIndicator2 = findViewById(R.id.indicator);
        circleIndicator2.attachToRecyclerView(bannerRCV, snapHelper);
        bannerAdapter.registerAdapterDataObserver(circleIndicator2.getAdapterDataObserver());
        bannerRCV.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1) {
                    stopAutoScrollBanner();

                }
                else
                {
                    position = bannerLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();

                }
            }
        });

    }

    private void stopAutoScrollBanner()
    {
        if (timer != null && timerTask != null)
        {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = bannerLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }

    private void runAutoScrollBanner(){
        if (timer == null && timerTask == null)
        {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    position = (++position) % bannerAdapter.getItemCount();
                    bannerRCV.smoothScrollToPosition(position);
                }
            };
            timer.schedule(timerTask, 4000, 4000);
        }
    }

    private List<String> getListBanner() {
        List<String> list = new ArrayList<>();
        list.add("https://cdn.tgdd.vn/hoi-dap/1311826/flashsale-la-gi-vao-nhung-ngay-nao-cach-san-flashsale22.jpg");
        list.add("https://cdn.24h.com.vn/upload/3-2020/images/2020-07-01/Luong-rung-rinh-mua-ngay-dung-ngai-voi-loat-uu-dai-sale-tram-ty-hom-nay-tren-Shopee-1-1593571735-380-width660height417.jpg");
        list.add("https://cdn.tgdd.vn/hoi-dap/1311826/flashsale-la-gi-vao-nhung-ngay-nao-cach-san-flashsale22.jpg");
        list.add("https://cdn.24h.com.vn/upload/3-2020/images/2020-07-01/Luong-rung-rinh-mua-ngay-dung-ngai-voi-loat-uu-dai-sale-tram-ty-hom-nay-tren-Shopee-1-1593571735-380-width660height417.jpg");
        return list;
    }

    private void prepareRecyclerItem() {
        menuRCV = findViewById(R.id.menuRCV);
        menuAdapter = new MenuAdaper(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        menuRCV.setLayoutManager(linearLayoutManager);
        menuAdapter.setData(getListMenu());
        menuRCV.setAdapter(menuAdapter);
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