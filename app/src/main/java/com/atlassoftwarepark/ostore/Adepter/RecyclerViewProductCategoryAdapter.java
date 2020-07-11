package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewProductCategoryAdapter extends RecyclerView.Adapter<RecyclerViewProductCategoryAdapter.ViewHolder> implements Filterable {

    private List<ProductItem> productItems;
    private List<ProductItem> productItemListFull;

    public RecyclerViewProductCategoryAdapter( List<ProductItem> productItems) {
        this.productItems = productItems;
        productItemListFull=new ArrayList<>(productItems);
    }

    @NonNull
    @Override
    public RecyclerViewProductCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_custom_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCategoryID.setText(productItems.get(position).getP_categoryID());
        holder.txtCategoryName.setText(productItems.get(position).getP_categoryName());
        holder.txtRegisteredDate.setText(productItems.get(position).getP_registeredDate());
        if (productItems.get(position).getP_categoryID().equals("#")) {
            holder.imageViewDelete.setVisibility(View.GONE);
            holder.txtAction.setText(productItems.get(position).getP_action());
        } else {
            holder.txtAction.setVisibility(View.GONE);
            holder.imageViewDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    @Override
    public Filter getFilter() {
        return pCategoryFilter;
    }

    private Filter pCategoryFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductItem> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(productItemListFull);
            }else{
                for(ProductItem category: productItemListFull){
                    if(category.getP_categoryName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(category);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productItems.clear();
            productItems.addAll((Collection<? extends ProductItem>) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryID,txtCategoryName, txtRegisteredDate, txtAction;
        ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryID=(TextView)itemView.findViewById(R.id.categoryID);
            txtCategoryName = (TextView) itemView.findViewById(R.id.categoryName);
            txtRegisteredDate = (TextView) itemView.findViewById(R.id.registeredDate);
            txtAction = (TextView) itemView.findViewById(R.id.productCategoryAction);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.deleteProductImageView);
        }
    }
}
