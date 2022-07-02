package com.example.shopie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import menu.Menu;
import menu.MenuAdaper;
import menu.MenuListener;

public class menuFragment extends Fragment {
    private RecyclerView menuRCV;
    private MenuAdaper menuAdapter;
    private FirebaseDatabase database;
    private List<Menu> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        list = new ArrayList<>();
        View view = inflater.inflate(R.layout.menu_recycler, container, false);
        menuRCV = view.findViewById(R.id.menuRCV);
        menuAdapter = new MenuAdaper(getContext(), new MenuListener() {
            @Override
            public void onItemClicked(Menu menu) {
//                List<String> items = new ArrayList<String>();
//                String url = "https://product.hstatic.net/1000176302/product/_webs_thumb_29cc88957c3f480c9398bf9bcc003b85_compact.jpg";
//                String name = "Bút chì cơ khí Ohto PROMECHA 1005P 0.5mm";
//                String price = "315.000";
//                String brand = "Ohto";
//                for (int i = 1; i <= 10; i++)
//                {
//                    items.add(Integer.toString(i));
//                }
//                Information info = new Information(items, url, name, price, brand);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("info_item", info);
//                Intent intent = new Intent(getActivity().getApplicationContext(), DescriptionActivity.class);
//                intent.putExtras(bundle);
//
//                startActivity(intent);

                Intent intent = new Intent(getActivity().getApplicationContext(), ItemActivity.class);
                intent.putExtra("category", menu.getName());
                startActivity(intent);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        menuRCV.setLayoutManager(linearLayoutManager);
        menuAdapter.setData(list);
        menuRCV.setAdapter(menuAdapter);

        database = FirebaseDatabase.getInstance();
        //Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        database.getReference().child("Menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Menu menu = dataSnapshot1.getValue(Menu.class);
                    list.add(menu);
                    //Toast.makeText(getContext(), menu.getName() + " " + menu.getUrlMenu(), Toast.LENGTH_SHORT).show();
                }
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }




}
