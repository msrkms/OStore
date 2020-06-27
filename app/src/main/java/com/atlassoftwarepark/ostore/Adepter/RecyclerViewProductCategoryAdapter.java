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

public class RecyclerViewProductCategoryAdapter extends RecyclerView.Adapter<RecyclerViewProductCategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductItem> productItems;

    public RecyclerViewProductCategoryAdapter(Context context,ArrayList<ProductItem>productItems){
        this.context=context;
        this.productItems=productItems;
    }

    @NonNull
    @Override
    public RecyclerViewProductCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCategoryName.setText(productItems.get(position).getP_categoryName());
        holder.txtRegisteredDate.setText(productItems.get(position).getP_registeredDate());
        holder.txtAction.setText(productItems.get(position).getP_action());
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName,txtRegisteredDate,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryName=(TextView)itemView.findViewById(R.id.categoryName);
            txtRegisteredDate=(TextView)itemView.findViewById(R.id.registeredDate);
            txtAction=(TextView)itemView.findViewById(R.id.productCategoryAction);
        }
    }
}
