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

public class RecyclerViewProfitLossAdapter extends RecyclerView.Adapter<RecyclerViewProfitLossAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProfitLossItem> profitLossItems;
    public RecyclerViewProfitLossAdapter(Context context,ArrayList<ProfitLossItem> profitLossItems){
        this.context=context;
        this.profitLossItems=profitLossItems;
    }

    @NonNull
    @Override
    public RecyclerViewProfitLossAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profit_loss_report_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProfitLossAdapter.ViewHolder holder, int position) {
        holder.txtProductName.setText(profitLossItems.get(position).getProductName());
        holder.txtTotalBuyRate.setText(profitLossItems.get(position).getTotalBuyRate());
        holder.txtTotalSellRate.setText((profitLossItems.get(position).getTotalSellRate()));
        holder.txtStatus.setText(profitLossItems.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return profitLossItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName,txtTotalBuyRate,txtTotalSellRate,txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName=(TextView)itemView.findViewById(R.id.productName);
            txtTotalBuyRate=(TextView)itemView.findViewById(R.id.totalBuyRate);
            txtTotalSellRate=(TextView)itemView.findViewById(R.id.totalSellRate);
            txtStatus=(TextView)itemView.findViewById(R.id.status);
        }
    }
}
