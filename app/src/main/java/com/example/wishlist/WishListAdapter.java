package com.example.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Wishlist> mWish;
    private SetOnClick setClick;

    public WishListAdapter(Context context, int resource, ArrayList<Wishlist> wishlists, SetOnClick setOnClick) {
        mContext = context;
        mResource = resource;
        mWish = wishlists;
        setClick = setOnClick;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(mResource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Wishlist wishlist = mWish.get(position);

        holder.product.setText(wishlist.getProduct());
        holder.price.setText(String.valueOf(wishlist.getPrice()));

        holder.editWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClick.onEdit(holder.getAdapterPosition());

            }
        });

        holder.deleteWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClick.onDelete(holder.getAdapterPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return mWish.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView product;
        TextView price;
        ImageView editWish;
        ImageView deleteWish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product = itemView.findViewById(R.id.tvProduct);
            price = itemView.findViewById(R.id.tvPrice);
            editWish = itemView.findViewById(R.id.ivEdit);
            deleteWish = itemView.findViewById(R.id.ivDelete);


        }
    }

    public interface SetOnClick {
        void onEdit (int position);
        void onDelete (int position);
    }
}
