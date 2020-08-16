package com.atlassoftwarepark.ostore;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.atlassoftwarepark.ostore.Object.Product;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProductGridAdepter extends RecyclerView.Adapter<ProductGridAdepter.MyViewHolder> implements Filterable {
    private Context context ;
    private List<Product> products ;
    private List<Product> productListFull;


    private View viewParrent;

    public ProductGridAdepter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        productListFull=new ArrayList<>(products);
    }

    @Override
    public ProductGridAdepter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        viewParrent=parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_grid_item,parent,false);
        ProductGridAdepter.MyViewHolder viewHolder=new ProductGridAdepter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(productListFull);
            }
            else {
                for(Product product: productListFull){
                    if(product.getCategory().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(product);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((Collection<? extends Product>) results.values);
            notifyDataSetChanged();
        }
    };




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textViewProductQty.setText(String.valueOf(products.get(position).getStock()));
        holder.materialButtonProduct.setText(products.get(position).getProductName());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHold.position=position;



            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {



        TextView textViewProductQty;
        MaterialButton materialButtonProduct;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            materialButtonProduct=(MaterialButton) itemView.findViewById(R.id.materialButtonProduct);
            textViewProductQty=(TextView) itemView.findViewById(R.id.textViewProductQty);
            cardView=(CardView) itemView.findViewById(R.id.cardViewProductItem_id) ;



        }
    }

    public interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
