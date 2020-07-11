package com.atlassoftwarepark.ostore.Adepter;

import android.app.Activity;
import android.content.Context;
import android.net.sip.SipSession;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.atlassoftwarepark.ostore.Object.ProductSell;
import com.atlassoftwarepark.ostore.ProductGridAdepter;
import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;
import java.util.List;

public class ProductSellRecyclerAdepter extends RecyclerView.Adapter<ProductSellRecyclerAdepter.ViewHolder> {


    private Context context;
    private ArrayList<ProductSell> productSellList;
    private View.OnKeyListener onKeyListener;

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
    public void onBindViewHolder(@NonNull final ProductSellRecyclerAdepter.ViewHolder holder, final int position) {

        if(position==0){
            holder.imageViewDelete.setVisibility(View.GONE);
            holder.editTextQty.setVisibility(View.GONE);
        }
        else{
            holder.textViewQty.setVisibility(View.GONE);
            holder.textViewAction.setVisibility(View.GONE);
            holder.textViewSl.setText(String.valueOf(position));
            holder.textViewProductName.setText(productSellList.get(position).getProduct().getProductName());
            holder.textViewUnitPrice.setText(String.valueOf(productSellList.get(position).getProduct().getUnitPrice()));
            holder.editTextQty.setText(String.valueOf(productSellList.get(position).getQty()));

        }

        holder.editTextQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int qty=0;
                try {
                    String QTY=holder.editTextQty.getText().toString();
                    if(QTY.length()>0){
                        qty=Integer.parseInt(QTY);
                    }
                }catch (Exception e){
                    System.out.println(e);
                }

                productSellList.get(position).setQty(qty);

                float totalprice=calculateTotalPrice(qty,position);
                holder.textViewPrice.setText(String.valueOf("সর্বমোট দাম:" +totalprice+" টাকা"));
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });


    }

    @Override
    public int getItemCount() {
        return productSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSl,textViewProductName, textViewQty, textViewUnitPrice, textViewAction,textViewPrice;

        EditText editTextQty;
        ImageView imageViewDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSl=itemView.findViewById(R.id.textViewSL);
            textViewProductName=itemView.findViewById(R.id.productName);
            textViewQty=itemView.findViewById(R.id.productQty);
            textViewAction=(TextView) itemView.findViewById(R.id.tvAction);
            textViewUnitPrice=(TextView) itemView.findViewById(R.id.tvUnitPrice);
            editTextQty=(EditText) itemView.findViewById(R.id.editTextproductQty);
            imageViewDelete=(ImageView) itemView.findViewById(R.id.deleteItemImageView);
            textViewPrice = (TextView) ((Activity)context).findViewById(R.id.textViewTotalPrice);



        }
    }

    public interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


   public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ProductSellRecyclerAdepter.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ProductSellRecyclerAdepter.ClickListener clicklistener) {

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

    private float calculateTotalPrice(int qty,int position){
        productSellList.get(position).setQty(qty);
        float totalPrice=0;
       for (int i=1;i<productSellList.size();i++){
           totalPrice=totalPrice+(productSellList.get(i).getProduct().getUnitPrice()*productSellList.get(i).getQty());
           System.out.println(productSellList.get(i).getQty());
       }
        DataHold.productSells=productSellList;
       return totalPrice;

    }


}
