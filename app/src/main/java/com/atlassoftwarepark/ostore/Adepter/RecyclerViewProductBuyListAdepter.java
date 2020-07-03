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

public class RecyclerViewProductBuyListAdepter extends RecyclerView.Adapter<RecyclerViewProductBuyListAdepter.ViewHolder> {

    private Context context;
    private ArrayList<ProductListItem> productListItems;

    public RecyclerViewProductBuyListAdepter(Context context, ArrayList<ProductListItem> productListItems) {
        this.context = context;
        this.productListItems = productListItems;
    }

    @NonNull
    @Override
    public RecyclerViewProductBuyListAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_buy_list_item,parent,false);
        RecyclerViewProductBuyListAdepter.ViewHolder viewHolder = new RecyclerViewProductBuyListAdepter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProductBuyListAdepter.ViewHolder holder, int position) {
        holder.textViewSl.setText(productListItems.get(position).getSL());
        holder.textViewBuyDate.setText(productListItems.get(position).getDate());
        holder.textViewAmmount.setText(productListItems.get(position).getAmount());
        holder.textViewVendor.setText(productListItems.get(position).getVendor());
        holder.textViewUnitPrice.setText(productListItems.get(position).getUnitPrice());
        holder.textViewTotalPrice.setText(productListItems.get(position).getTotalPrice());
        holder.textViewAction.setText(productListItems.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return productListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSl,textViewBuyDate,textViewAmmount,textViewVendor,textViewUnitPrice,textViewTotalPrice,textViewAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSl=(TextView)itemView.findViewById(R.id.textViewSl);
            textViewBuyDate=(TextView)itemView.findViewById(R.id.textViewDate);
            textViewAmmount=(TextView)itemView.findViewById(R.id.textViewAmount);
            textViewVendor=(TextView)itemView.findViewById(R.id.textViewVendor);
            textViewUnitPrice=(TextView)itemView.findViewById(R.id.textViewUnitPrice);
            textViewTotalPrice=(TextView)itemView.findViewById(R.id.textViewTotalPrice);
            textViewAction=(TextView)itemView.findViewById(R.id.textViewAction);

        }
    }
}