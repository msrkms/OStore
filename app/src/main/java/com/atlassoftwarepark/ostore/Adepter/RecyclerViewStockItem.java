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

public class RecyclerViewStockItem extends RecyclerView.Adapter<RecyclerViewStockItem.ViewHolder> {
    private Context context;
    private ArrayList<StockItem> stockItems;

    public RecyclerViewStockItem(Context contex,ArrayList<StockItem> stockItems){
        this.context=contex;
        this.stockItems=stockItems;
        System.out.println(stockItems.size());
    }


    @NonNull
    @Override
    public RecyclerViewStockItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_custom_list,parent,false);
        RecyclerViewStockItem.ViewHolder viewHolder=new RecyclerViewStockItem.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SL.setText(stockItems.get(position).getSlNo());
        holder.productname.setText(stockItems.get(position).getProductname());
        holder.quantity.setText(stockItems.get(position).getQuantity());
        holder.unitprice.setText(stockItems.get(position).getUnitprice());
        holder.status.setText(stockItems.get(position).getStatus());
    }


    @Override
    public int getItemCount() {
        return stockItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SL,productname,quantity,unitprice,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SL=(TextView)itemView.findViewById(R.id.SlNo);
            productname=(TextView)itemView.findViewById(R.id.productName);
            quantity=(TextView)itemView.findViewById(R.id.quantity);
            unitprice=(TextView)itemView.findViewById(R.id.unitPrice);
            status=(TextView)itemView.findViewById(R.id.status);
        }
    }
}
