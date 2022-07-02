package com.example.shopie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Information.Information;
import menu.Menu;
import menu.MenuAdaper;
import menu.MenuListener;

public class menuFragment extends Fragment {
    private RecyclerView menuRCV;
    private MenuAdaper menuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_recycler, container, false);
        menuRCV = view.findViewById(R.id.menuRCV);
        menuAdapter = new MenuAdaper(getContext(), new MenuListener() {
            @Override
            public void onItemClicked(Menu menu) {
                List<String> items = new ArrayList<String>();
                String url = "https://product.hstatic.net/1000176302/product/_webs_thumb_29cc88957c3f480c9398bf9bcc003b85_compact.jpg";
                String name = "Bút chì cơ khí Ohto PROMECHA 1005P 0.5mm";
                String price = "315.000";
                String brand = "Ohto";
                for (int i = 1; i <= 10; i++)
                {
                    items.add(Integer.toString(i));
                }
                Information info = new Information(items, url, name, price, brand);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info_item", info);
                Intent intent = new Intent(getActivity().getApplicationContext(), DescriptionActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        menuRCV.setLayoutManager(linearLayoutManager);
        menuAdapter.setData(getListMenu());
        menuRCV.setAdapter(menuAdapter);
        return view;
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
}
