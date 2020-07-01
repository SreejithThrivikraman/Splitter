package com.thrivikraman.sreejith.dev.splitter.views.ui.friends;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thrivikraman.sreejith.dev.splitter.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.MyViewHolder> {

    Context context;
    String s1[];

    public FriendListAdapter(Context ct, String s1[]) {

        this.context = ct;
        this.s1 = s1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friendlistitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.FriendName.setText(s1[position]);
        holder.FriendImage.setImageResource(R.drawable.coin_us_dollar_icon_48);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView FriendName;
        ImageView FriendImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            FriendName = itemView.findViewById(R.id.friendName);
            FriendImage = itemView.findViewById(R.id.friendImage);
        }
    }
}