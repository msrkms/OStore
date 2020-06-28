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
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.EProductItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewEProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EveryProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EveryProductFragment extends Fragment {

    LinearLayout layoutAddNewProduct;
    RecyclerView recyclerView;
    ArrayList<EProductItem>eProductItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EveryProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EveryProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EveryProductFragment newInstance(String param1, String param2) {
        EveryProductFragment fragment = new EveryProductFragment();
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
        View eProduct = inflater.inflate(R.layout.fragment_every_product, container, false);

        layoutAddNewProduct=(LinearLayout)eProduct.findViewById(R.id.layoutAddNewProductInfo);
        recyclerView = (RecyclerView)eProduct.findViewById(R.id.recyclerViewProductList);


        eProductItems=new ArrayList<EProductItem>();
        EProductItem eProductItem=new EProductItem();
        eProductItem.setProductCategory("ক্যাটাগরি");
        eProductItem.setProductName("প্রোডাক্ট নাম");
        eProductItem.setProductUnit("প্রোডাক্ট ইউনিট");
        eProductItem.setProductUnitPrice("ইউনিট মূল্য");
        eProductItem.setProductAction("অ্যাকশান");

        eProductItems.add(eProductItem);

        RecyclerViewEProductAdapter recyclerViewEProductAdapter= new RecyclerViewEProductAdapter(getContext(),eProductItems);
        recyclerView.setAdapter(recyclerViewEProductAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        layoutAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        return eProduct;
    }

    private void showDialog(){

        View addProductInfo = LayoutInflater.from(getContext()).inflate(R.layout.add_new_product_info_layout,null);

        ImageButton closeDialog;
        Spinner productCategory;

        closeDialog =(ImageButton)addProductInfo.findViewById(R.id.closeDialogNewProductInfo);
        productCategory=(Spinner)addProductInfo.findViewById(R.id.spinnerChooseProductCategory);


        ArrayAdapter<String> arrayAdapterProductCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getProductCategory()
        );
        productCategory.setAdapter(arrayAdapterProductCategory);

        final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setView(addProductInfo).create();
        alertDialog.show();

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private List<String> getProductCategory(){
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("NA");
        stringsCategory.add("NA");

        return stringsCategory;

    }

}