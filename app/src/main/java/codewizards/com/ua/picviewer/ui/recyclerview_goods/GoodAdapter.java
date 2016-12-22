package codewizards.com.ua.picviewer.ui.recyclerview_goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import codewizards.com.ua.picviewer.ui.activities.MainActivity;
import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.model.Good;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Created by Интернет on 21.12.2016.
 */

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.ViewHolder> {
    private Context context;
    private List<Good> listOfGoods;

    public GoodAdapter(Context context, List<Good> listOfGoods) {
        this.context = context;
        this.listOfGoods = listOfGoods;
    }

    @Override
    public GoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_good, parent, false);

        final MainActivity itemClickListener = (MainActivity) context;
        final ViewHolder viewHolder = new ViewHolder(contactView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                if (pos != NO_POSITION) {
                    itemClickListener.onClick(pos);
                }
            };
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Good good = listOfGoods.get(position);

        holder.tvName.setText(good.getName());
        holder.tvPrice.setText(String.valueOf(good.getPrice()));
        Glide.with(context).load(good.getUrl()).placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_placeholder).into(holder.ivPic);
    }

    @Override
    public int getItemCount() {
        return listOfGoods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPic;
        public TextView tvName;
        public TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.pic);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

    public void setListOfGoods(List<Good> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }
}
