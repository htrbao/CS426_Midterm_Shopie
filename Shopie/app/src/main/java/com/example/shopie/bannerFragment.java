package com.example.shopie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import banner.BannerAdapter;
import me.relex.circleindicator.CircleIndicator2;

public class bannerFragment extends Fragment {
    private RecyclerView bannerRCV;
    private BannerAdapter bannerAdapter;
    private CircleIndicator2 circleIndicator2;
    private LinearLayoutManager bannerLinearLayoutManager;
    private Timer timer;
    private TimerTask timerTask;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.banner_fragment, container, false);
        List<String> urls = getListBanner();
        bannerRCV = view.findViewById(R.id.bannerRCV);
        bannerAdapter = new BannerAdapter(getContext(), urls);
        bannerLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
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
        circleIndicator2 = view.findViewById(R.id.indicator);
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
        return view;
    }

    public void stopAutoScrollBanner()
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

    public void runAutoScrollBanner(){
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

}
