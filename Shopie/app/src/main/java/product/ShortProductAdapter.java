package product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopie.R;

import java.util.List;

import menu.MenuListener;

public class ShortProductAdapter extends RecyclerView.Adapter<ShortProductAdapter.ShortProductViewHolder> {



    private Context mContext;
    private List<Product> productList;
    private ProductListener listener;

    public ShortProductAdapter(Context mContext, ProductListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void setData(List<Product> productList)
    {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShortProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent, false);

        return new ShortProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null)
            return;

        Glide.with(mContext).load(product.getUrlProduct()).fitCenter().into(holder.imgItem);
        holder.tvNameItem.setText(product.getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null)
        {
            return productList.size();
        }
        return 0;
    }

    class ShortProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvNameItem;
        private CardView cardView;
        public ShortProductViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.show_item_cardview);

            imgItem = itemView.findViewById(R.id.img_show_item);
            tvNameItem = itemView.findViewById(R.id.tv_name_item);

        }
    }
}
