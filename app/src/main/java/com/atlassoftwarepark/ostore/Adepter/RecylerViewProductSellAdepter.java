package com.atlassoftwarepark.ostore.Adepter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.Object.Product;
import com.atlassoftwarepark.ostore.Object.ProductSell;
import com.atlassoftwarepark.ostore.R;

import java.util.List;

public class RecylerViewProductSellAdepter extends RecyclerView.Adapter<RecylerViewProductSellAdepter.ViewHolder> {

    public RecylerViewProductSellAdepter(List<ProductSell> productSellList) {
        this.productSellList = productSellList;

    }

    private List<ProductSell> productSellList;

    @NonNull
    @Override
    public RecylerViewProductSellAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("ss");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_sell,parent,false);
        RecylerViewProductSellAdepter.ViewHolder viewHolder=new RecylerViewProductSellAdepter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {


     /*   if(position==0){
            holder.imageViewDelete.setVisibility(View.GONE);
            holder.textViewSl.setText("#");
            holder.textViewProductName.setText("পণ্যের নাম");
            holder.textViewAction.setText("অ্যাকশন");
            holder.textViewQty.setText("পরিমাণ");
        }
        else{
            holder.textViewAction.setVisibility(View.GONE);
            holder.textViewQty.setVisibility(View.GONE);
            holder.editTextQty.setVisibility(View.VISIBLE);
            holder.imageViewDelete.setVisibility(View.VISIBLE);

            holder.textViewSl.setText(productSellList.get(position).getId());
            holder.textViewProductName.setText(productSellList.get(position).getProduct().getProductName());
            holder.textViewUnitPrice.setText(String.valueOf(productSellList.get(position).getProduct().getUnitPrice()));
        }*/



    }

    @Override
    public int getItemCount() {
        System.out.println("Product"+productSellList.size());
        return productSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSl,textViewProductName, textViewQty, textViewUnitPrice, textViewAction;
        EditText editTextQty;
        ImageView imageViewDelete;
        public ViewHolder( View itemView) {
            super(itemView);

            textViewSl=itemView.findViewById(R.id.textViewSL);
            textViewProductName=itemView.findViewById(R.id.productName);
            textViewQty=itemView.findViewById(R.id.productQty);
            textViewAction=(TextView) itemView.findViewById(R.id.tvAction);
            textViewUnitPrice=(TextView) itemView.findViewById(R.id.tvUnitPrice);
            editTextQty=(EditText) itemView.findViewById(R.id.editTextproductQty);
            imageViewDelete=(ImageView) itemView.findViewById(R.id.deleteItemImageView);
        }
    }
}
