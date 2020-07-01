package com.thrivikraman.sreejith.dev.splitter.views.ui.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.thrivikraman.sreejith.dev.splitter.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class groupListAdapter extends RecyclerView.Adapter<groupListAdapter.MyViewHolder> {

    Context context;
    String s1[];

    public groupListAdapter(Context ct, String s1[]) {

        this.context = ct;
        this.s1 = s1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grouplistitem,parent,false);
        return new groupListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.groupName.setText(s1[position]);
        holder.groupImage.setImageResource(R.drawable.coin_us_dollar_icon_48);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView groupName;
        ImageView groupImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.groupName);
            groupImage = itemView.findViewById(R.id.groupImage);
        }


    }
}
