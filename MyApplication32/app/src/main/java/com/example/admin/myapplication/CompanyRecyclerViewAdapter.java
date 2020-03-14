package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Admin on 04-10-2019.
 */

public class CompanyRecyclerViewAdapter extends RecyclerView.Adapter<CompanyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mCompanyNames=new ArrayList<>();
    private Context mcontext;

    public CompanyRecyclerViewAdapter(ArrayList<String> mCompanyNames, Context mcontext) {
        this.mCompanyNames = mCompanyNames;
        this.mcontext = mcontext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_company_info,parent,false);
        CompanyRecyclerViewAdapter.ViewHolder viewHolder=new CompanyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.companyName.setText(mCompanyNames.get(position));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mcontext,FullCompanyActivity.class);
                intent.putExtra("companyname",mCompanyNames.get(position));
                mcontext.startActivity(intent);
                Toast.makeText(mcontext, "successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCompanyNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView companyName;
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

        companyName=(TextView) itemView.findViewById(R.id.company_name);
        relativeLayout=(RelativeLayout) itemView.findViewById(R.id.single_company_relative);
        }
    }
}
