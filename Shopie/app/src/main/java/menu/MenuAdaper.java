package menu;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopie.R;

import java.util.List;

public class MenuAdaper extends RecyclerView.Adapter<MenuAdaper.MenuViewHolder> {
    private List<Menu> menuList;
    private Context context;

    public MenuAdaper(Context context) {
        this.context = context;
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

        holder.imgItem.setImageResource(menu.getResourceID());
        holder.tvItem.setText(menu.getTitle());
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
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            tvItem = itemView.findViewById(R.id.tv_item);

        }
    }
}
