package com.carson.travelwishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder>
{
    private List<String> data;

    private WishListClickListener listener;

    public WishListAdapter(List<String> data, WishListClickListener listener){
        this.listener = listener;
        this.data = data;
    }

    //Objects of this class represent the view for one data item
    static class WishListViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {

        TextView textView;
        WishListClickListener listener;

        WishListViewHolder(TextView v, WishListClickListener listener){
            super(v);
            this.listener = listener;
            textView = v;
            textView.setOnClickListener(this);
            textView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view){

            listener.onListClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view){

            listener.onListLongClick(getAdapterPosition());
            return true;
        }
    }


    @NonNull
    @Override
    public WishListAdapter.WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wish_list_element, parent, false);

        WishListViewHolder viewHolder = new WishListViewHolder(textView, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.WishListViewHolder holder, int position) {

        String text = data.get(position);
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
