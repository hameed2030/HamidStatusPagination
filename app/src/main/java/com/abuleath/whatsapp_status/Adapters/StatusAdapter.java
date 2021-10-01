package com.abuleath.whatsapp_status.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abuleath.whatsapp_status.ButtonsFunctions;
import com.abuleath.whatsapp_status.Model.DBHelper;
import com.abuleath.whatsapp_status.R;
import com.abuleath.whatsapp_status.Status;

import java.util.ArrayList;


public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{

    ArrayList<Status> statuses;
    DBHelper localDB;
    ButtonsFunctions btnFun = new ButtonsFunctions();
    public StatusAdapter(Context context , ArrayList<Status> statuses) {
        this.statuses = statuses;
        localDB = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_row , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
           final String status = statuses.get(i).getStatusText();
           final int statusId = statuses.get(i).getStatusId();
           holder.status.setText(status);

        holder.fav_btn.setBackgroundResource(R.drawable.customborder);
        holder.wh_share_btn.setBackgroundResource(R.drawable.customborder);
        holder.share_btn.setBackgroundResource(R.drawable.customborder);
        ////// setOnClickListener to marke status is favorite
        if(localDB.isFavorite(statusId))
        {
            holder.fav_btn.setImageResource(R.drawable.ic_favorite_red_24dp);
        }

        else
        {
            holder.fav_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!localDB.isFavorite(statusId))
                {
                    localDB.addToFavorites(statusId);
                    holder.fav_btn.setImageResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(view.getContext().getApplicationContext() , "تم إضافة الحالة إلى المفضلة" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    localDB.removeFromFavorites(statusId);
                    holder.fav_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(view.getContext().getApplicationContext() , "تم إزالة الحالة من المفضلة" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////////////////////////////////////////////////

           holder.copybtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   btnFun.CopyBtn(view , status);
               }
           });

           holder.wh_share_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   btnFun.wh_share_btn(view , status);
               }
           });

           holder.share_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   btnFun.ShareBtn(view , status);
               }
           });
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView status;
        ImageView copybtn , wh_share_btn , share_btn , fav_btn;
        public ViewHolder(View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.status);
            copybtn = (ImageView) itemView.findViewById(R.id.copy_btn);
            wh_share_btn = (ImageView) itemView.findViewById(R.id.whatsapp_btn);
            share_btn = (ImageView) itemView.findViewById(R.id.share_btn);
            fav_btn = (ImageView) itemView.findViewById(R.id.fav_btn);
        }
    }

    public void addStatuses(ArrayList<Status> newstatuses)
    {
        for (Status t : newstatuses)
        {
            statuses.add(t);
        }

        notifyDataSetChanged();
    }

}
