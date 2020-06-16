package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.NotificationSpinnerItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewSellReportAdapter;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewStockItem;
import com.atlassoftwarepark.ostore.Adepter.SellReportItem;
import com.atlassoftwarepark.ostore.Adepter.StockItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StockFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner spinnerCategory;
    private ArrayList<StockItem> stockItem;
    RecyclerView recyclerView;



    private String mParam1;
    private String mParam2;

    public StockFragment() {
    }

    public static StockFragment newInstance(String param1, String param2) {
        StockFragment fragment = new StockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewStock= inflater.inflate(R.layout.fragment_stock, container, false);

        spinnerCategory=viewStock.findViewById(R.id.spinnerCategory);
        recyclerView=viewStock.findViewById(R.id.recyclerStock);
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        spinnerCategory.setAdapter(arrayAdapterCategory);


        stockItem= new ArrayList<StockItem>();
        StockItem stockItem0= new StockItem();
        stockItem0.setSlNo("#");
        stockItem0.setProductname("প্রোডাক্ট নাম");
        stockItem0.setQuantity("স্টক সংখ্যা");
        stockItem0.setUnitprice("ইউনিট");
        stockItem0.setStatus("স্ট্যাটাস");
        stockItem.add(stockItem0);

        StockItem stockItem1= new StockItem();
        stockItem1.setSlNo("01");
        stockItem1.setProductname("Product One");
        stockItem1.setQuantity("100");
        stockItem1.setUnitprice("10$");
        stockItem1.setStatus("Available");
        stockItem.add(stockItem1);
        StockItem stockItem2= new StockItem();
        stockItem2.setSlNo("01");
        stockItem2.setProductname("Product One");
        stockItem2.setQuantity("100");
        stockItem2.setUnitprice("10$");
        stockItem2.setStatus("Available");
        stockItem.add(stockItem2);

        RecyclerViewStockItem recyclerViewStockItem= new RecyclerViewStockItem(getContext(),stockItem);
        recyclerView.setAdapter(recyclerViewStockItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return viewStock;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        stringsCategory.add("Product 1");
        stringsCategory.add("Product 2");

        return stringsCategory;

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}