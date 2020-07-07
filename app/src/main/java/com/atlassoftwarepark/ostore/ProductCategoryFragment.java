package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atlassoftwarepark.ostore.Adepter.ProductItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProductCategoryAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductCategoryFragment extends Fragment {

    LinearLayout linearAddProduct;
    RecyclerView recyclerView;

    ArrayList<ProductItem> productItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductCategoryFragment newInstance(String param1, String param2) {
        ProductCategoryFragment fragment = new ProductCategoryFragment();
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
        View productCategory = inflater.inflate(R.layout.fragment_product_category, container, false);

        linearAddProduct=(LinearLayout)productCategory.findViewById(R.id.layoutAddProduct);
        recyclerView=(RecyclerView)productCategory.findViewById(R.id.recyclerViewProductCategoryList);

        productItems=new ArrayList<ProductItem>();
        ProductItem productItem = new ProductItem();
        productItem.setP_categoryName("ক্যাটাগরি নাম");
        productItem.setP_registeredDate("নিবন্ধিত তারিখ");
        productItem.setP_action("অ্যাকশন");

        productItems.add(productItem);

        RecyclerViewProductCategoryAdapter recyclerViewProductCategoryAdapter = new RecyclerViewProductCategoryAdapter(getContext(),productItems);
        recyclerView.setAdapter(recyclerViewProductCategoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        linearAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return productCategory;
    }

    private  void showDialog(){

        View addProduct = LayoutInflater.from(getContext()).inflate(R.layout.add_new_product_layout,null);

        ImageButton closeDialogProduct;
        ImageView dateSelect;
        MaterialButton materialButtonAdd;
        final TextInputEditText textInputEditTextDate,textInputEditTextProductCategory;
        closeDialogProduct=(ImageButton)addProduct.findViewById(R.id.closeDialogProduct);
        dateSelect=(ImageView)addProduct.findViewById(R.id.addProductDateSelect);
        textInputEditTextDate=(TextInputEditText)addProduct.findViewById(R.id.textInputEditTextAddProductDate);
        textInputEditTextProductCategory=(TextInputEditText)addProduct.findViewById(R.id.textInputEditTextProductCategory);
        materialButtonAdd=(MaterialButton) addProduct.findViewById(R.id.TransactionAddBtn);

        materialButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Category=textInputEditTextProductCategory.getText().toString();
                String Date=textInputEditTextDate.getText().toString();
                System.out.println(Category+Date);

            }
        });

        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select a Date");
                final MaterialDatePicker addProductDatePicker = builder.build();
                addProductDatePicker.show(getFragmentManager(),"DatePicker");
                addProductDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textInputEditTextDate.setText(addProductDatePicker.getHeaderText());
                        
                    }
                });

            }
        });

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setView(addProduct).create();
        alertDialog.show();

        closeDialogProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

}