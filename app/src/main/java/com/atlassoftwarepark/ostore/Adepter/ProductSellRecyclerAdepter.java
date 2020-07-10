package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.Object.ProductSell;
import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;
import java.util.List;

public class ProductSellRecyclerAdepter extends RecyclerView.Adapter<ProductSellRecyclerAdepter.ViewHolder> {

    private Context context;
    private List<ProductSell> productSellList;

    public ProductSellRecyclerAdepter(Context context,ArrayList<ProductSell>productSellList){
        this.context=context;
        this.productSellList=productSellList;
    }

    @NonNull
    @Override
    public ProductSellRecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_sell,parent,false);
        ProductSellRecyclerAdepter.ViewHolder viewHolder=new ProductSellRecyclerAdepter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSellRecyclerAdepter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        System.out.println("s"+productSellList.size());
        return productSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
