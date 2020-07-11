package com.atlassoftwarepark.ostore.Adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RecyclerViewProductSellAdapter extends RecyclerView.Adapter<RecyclerViewProductSellAdapter.ViewHolder> {

    private ArrayList<SelectedProductItem>selectedProductItems;

    public RecyclerViewProductSellAdapter(ArrayList<SelectedProductItem> selectedProductItems){
        this.selectedProductItems=selectedProductItems;
    }

    @NonNull
    @Override
    public RecyclerViewProductSellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_product_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProductSellAdapter.ViewHolder holder, int position) {
        holder.txtSlno.setText(selectedProductItems.get(position).getS_productSL());
        holder.pName.setText(selectedProductItems.get(position).getS_productName());
        holder.pQuantity.setText(selectedProductItems.get(position).getS_productQuantity());
        holder.pUnitPrice.setText(selectedProductItems.get(position).getS_productUnitPrice());
    }

    @Override
    public int getItemCount() {
        return selectedProductItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSlno;
        TextInputEditText pName,pQuantity,pUnitPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSlno=(TextView)itemView.findViewById(R.id.serialNoSelected);
            pName=(TextInputEditText)itemView.findViewById(R.id.productNameSelected);
            pQuantity=(TextInputEditText)itemView.findViewById(R.id.productQuantitySelected);
            pUnitPrice=(TextInputEditText)itemView.findViewById(R.id.productUnitPriceSelected);
        }
    }
}
