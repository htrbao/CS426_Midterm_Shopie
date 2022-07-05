package product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopie.R;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>{
    private Context mContext;
    private List<Product> productList;
    private CartProductListener listener;

    public CartProductAdapter(Context mContext, CartProductListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new CartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartProduct cartProduct = Cart.cartProductList.get(position);
        if(cartProduct == null) return;
        Glide.with(mContext).load(cartProduct.getUrlProduct()).fitCenter().into(holder.imageView);
        holder.tvProductName.setText(cartProduct.getName());
        holder.tvType.setText(cartProduct.getType());
        holder.tvPrice.setText(cartProduct.getPrice());
        holder.etQuantity.setText(cartProduct.getQuantity());

        holder.etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().matches("0")) {
                    listener.onFocusQuanEdt(position, s.toString());
                    Cart.cartProductList.remove(position);
                    notifyDataSetChanged();
                }
                listener.onFocusQuanEdt(position, (s.toString().matches("") ? "1" : s.toString()));
            }
        });

        holder.incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickIncBtn(position);
                holder.etQuantity.setText(cartProduct.getQuantity());
            }
        });

        holder.descBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = false;
                if(cartProduct.getQuantity().matches("0")) {
                    Cart.cartProductList.remove(position);
                    notifyDataSetChanged();
                    b = true;
                    listener.onClickDescBtn(position);
                    return;
                }
                listener.onClickDescBtn(position);
                if(!b) holder.etQuantity.setText(cartProduct.getQuantity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (Cart.cartProductList == null ? 0 : Cart.cartProductList.size());
    }

    public class CartProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvProductName, tvType, tvPrice;
        private EditText etQuantity;
        private ImageButton incBtn, descBtn;

        public CartProductViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.cart_img_view);
            tvProductName = (TextView) itemView.findViewById(R.id.cart_item_name);
            tvType = (TextView) itemView.findViewById(R.id.cart_item_type);
            tvPrice = (TextView) itemView.findViewById(R.id.cart_item_price);
            etQuantity = (EditText) itemView.findViewById(R.id.cart_quantity);

            incBtn = (ImageButton) itemView.findViewById(R.id.inc_btn);
            descBtn = (ImageButton) itemView.findViewById(R.id.des_btn);
        }
    }
}
