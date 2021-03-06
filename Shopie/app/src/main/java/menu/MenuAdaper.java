package menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopie.R;

import java.util.List;

public class MenuAdaper extends RecyclerView.Adapter<MenuAdaper.MenuViewHolder> {
    private List<Menu> menuList;
    private Context context;
    private MenuListener listener;

    public MenuAdaper(Context context, MenuListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<Menu> list)
    {
        this.menuList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        if (menu == null)
            return;

        Glide.with(context).load(menu.getUrlMenu()).fitCenter().into(holder.imgItem);
        holder.tvItem.setText(menu.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (menuList != null)
        {
            return menuList.size();
        }
        return 0;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgItem;
        private TextView tvItem;
        private CardView cardView;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            tvItem = itemView.findViewById(R.id.tv_item);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
