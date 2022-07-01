package banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopie.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerHolder> {

    private List<String> urls;
    private Context context;

    public BannerAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, parent, false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHolder holder, int position) {
        String url = urls.get(position % urls.size());

        Glide.with(context).load(url).centerCrop().into(holder.banner_img);
    }

    @Override
    public int getItemCount() {
        if (urls != null)
        {
            return urls.size();
        }
        return 0;
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        private ImageView banner_img;
        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            banner_img = itemView.findViewById(R.id.img_banner);
        }
    }
}
