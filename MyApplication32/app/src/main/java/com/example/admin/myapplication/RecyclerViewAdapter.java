package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 01-10-2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private final String TAG="RecyclerViewAdapter";
    private ArrayList<String> mtitles=new ArrayList<>();
    private ArrayList<String> mdescriptions=new ArrayList<>();
    private Context mcontext;
    public RecyclerViewAdapter(Context context, ArrayList<String> titles, ArrayList<String> descriptions) {
        Log.d(TAG, "RecyclerViewAdapter: constructor called ");
        this.mtitles = titles;
        this.mdescriptions = descriptions;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "RecyclerViewAdapter: onCreateViewHolder called ");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d(TAG, "RecyclerViewAdapter: onBindViewHolder called ");
        holder.title.setText(mtitles.get(position));
        holder.description.setText(mdescriptions.get(position));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,FullNotificationActivity.class);
                intent.putExtra("notification_title",mtitles.get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "RecyclerViewAdapter: getItemCount "+ mtitles.size());
        return mtitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,description;
        RelativeLayout parent;
        public ViewHolder(View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.notify_title);
            description=(TextView)itemView.findViewById(R.id.notify_description);
            parent=(RelativeLayout) itemView.findViewById(R.id.single_relative);

        }
    }
}
