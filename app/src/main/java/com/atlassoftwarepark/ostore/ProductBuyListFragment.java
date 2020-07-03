package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.ProductListItem;
import com.atlassoftwarepark.ostore.Adepter.QuickSellItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProductBuyListAdepter;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewQuickSellAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductBuyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductBuyListFragment extends Fragment {

    private Spinner spinnerCategory;

    ArrayList<ProductListItem> productListItems;

    MaterialButton materialButtonProduct0,materialButtonProduct1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductBuyListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductBuyListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductBuyListFragment newInstance(String param1, String param2) {
        ProductBuyListFragment fragment = new ProductBuyListFragment();
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
        // Inflate the layout for this fragment
        View viewProductBuyList= inflater.inflate(R.layout.fragment_product_buy_list, container, false);

        materialButtonProduct0=(MaterialButton) viewProductBuyList.findViewById(R.id.materialButtonProduct0);
        materialButtonProduct1=(MaterialButton) viewProductBuyList.findViewById(R.id.materialButtonProduct1);


        materialButtonProduct0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProductListItem();
            }
        });

        materialButtonProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProductListItem();
            }
        });


        productListItems = new ArrayList<ProductListItem>();

        ProductListItem productListItem = new ProductListItem();
        productListItem.setSL("#");
        productListItem.setDate("ক্রয়ের তারিখ");
        productListItem.setAmount("পরিমাণ");
        productListItem.setVendor("ভেন্ডর");
        productListItem.setUnitPrice("প্রতি ইউনিট মূল্য");
        productListItem.setTotalPrice("সর্বমোট মূল্য");
        productListItem.setAction("অ্যাকশন");
        productListItems.add(productListItem);

        spinnerCategory=viewProductBuyList.findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );

        spinnerCategory.setAdapter(arrayAdapterCategory);

        return viewProductBuyList;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        stringsCategory.add("Product 1");
        stringsCategory.add("Product 2");

        return stringsCategory;

    }

    private void showProductListItem(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.product_buy_list,null);

        ImageButton closeDialog;
        RecyclerView recyclerView;
        closeDialog=(ImageButton)view.findViewById(R.id.closeDialogProductBuyList);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerViewProducts);


        RecyclerViewProductBuyListAdepter recyclerViewProductBuyListAdepter=new RecyclerViewProductBuyListAdepter(getContext(),productListItems);
        recyclerView.setAdapter(recyclerViewProductBuyListAdepter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setView(view).create();
        alertDialog.show();

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}