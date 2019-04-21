package com.example.android.fcisactivities.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.fcisactivities.Model.FcisActivity;
import com.example.android.fcisactivities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {
   private Context context;
   private ArrayList<FcisActivity>activities;

    public MainAdapter(Context context, ArrayList<FcisActivity> activities) {
        this.context = context;
        this.activities = activities;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_row_item,parent,false);
    return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
    FcisActivity activity=activities.get(position);
    holder.bind(activity);

    }

    @Override
    public int getItemCount() {
        return (activities == null) ? 0 : activities.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        ImageView activityImage;
        TextView activityName;
        TextView activityYear;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            activityImage=(ImageView)itemView.findViewById(R.id.activity_image);
            activityName=(TextView)itemView.findViewById(R.id.activity_name);
            activityYear=(TextView)itemView.findViewById(R.id.activity_year);
        }
        public void bind(FcisActivity activity)
        {
            Picasso.with(context).load(activity.getBackGround())
                    .placeholder(R.color.colorAccent).into(activityImage);
            Integer intt=activity.getYear();
            String Year = Integer.toString(intt);

            activityYear.setText(Year);
            activityName.setText(activity.getName());
        }
    }
}
