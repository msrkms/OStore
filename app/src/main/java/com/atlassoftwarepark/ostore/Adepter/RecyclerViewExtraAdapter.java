package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;

public class RecyclerViewExtraAdapter extends RecyclerView.Adapter<RecyclerViewExtraAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ExtraItem> extraItems;

    public RecyclerViewExtraAdapter(Context context,ArrayList<ExtraItem> extraItems){
        this.context=context;
        this.extraItems=extraItems;
    }

    @NonNull
    @Override
    public RecyclerViewExtraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_extra_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewExtraAdapter.ViewHolder holder, int position) {
        holder.txtFrom.setText(extraItems.get(position).getFrom());
        holder.txtDate.setText(extraItems.get(position).getDate());
        holder.txtAmount.setText(extraItems.get(position).getAmount());
        holder.txtStatus.setText(extraItems.get(position).getStatus());
        holder.txtAction.setText(extraItems.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return extraItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFrom,txtDate,txtAmount,txtStatus,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFrom=(TextView)itemView.findViewById(R.id.from);
            txtDate=(TextView)itemView.findViewById(R.id.date);
            txtAmount=(TextView)itemView.findViewById(R.id.amount);
            txtStatus=(TextView)itemView.findViewById(R.id.status);
            txtAction=(TextView)itemView.findViewById(R.id.Action);
        }
    }
}
