package com.atlassoftwarepark.ostore;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.atlassoftwarepark.ostore.Object.Product;
import com.google.android.material.button.MaterialButton;

import java.util.List;


public class ProductGridAdepter extends RecyclerView.Adapter<ProductGridAdepter.MyViewHolder> {
    private Context context ;
    private List<Product> products ;

    private View viewParrent;

    public ProductGridAdepter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductGridAdepter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        viewParrent=parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_grid_item,parent,false);
        ProductGridAdepter.MyViewHolder viewHolder=new ProductGridAdepter.MyViewHolder(view);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textViewProductQty.setText(String.valueOf(products.get(position).getStock()));
        holder.textViewProduct.setText(products.get(position).getProductName());


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
        TextView textViewProduct;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewProduct=(TextView) itemView.findViewById(R.id.textViewProduct);
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
