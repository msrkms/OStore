package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.Adepter.ProductItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProductCategoryAdapter;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewVendorAdapter;
import com.atlassoftwarepark.ostore.Adepter.VendorItem;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductCategoryFragment extends Fragment {

    LinearLayout linearAddProduct;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    List<ProductItem> productItems;
    RecyclerViewProductCategoryAdapter recyclerViewProductCategoryAdapter;
    SearchView searchViewCategory;

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
        searchViewCategory=(SearchView)productCategory.findViewById(R.id.searchProductCategory);

        productItems=new ArrayList<ProductItem>();
        ProductItem productItem = new ProductItem();
        productItem.setP_categoryID("#");
        productItem.setP_categoryName("ক্যাটাগরি নাম");
        productItem.setP_registeredDate("নিবন্ধিত তারিখ");
        productItem.setP_action("অ্যাকশন");

        productItems.add(productItem);

        getCategory();

        recyclerViewProductCategoryAdapter = new RecyclerViewProductCategoryAdapter(productItems);
        recyclerView.setAdapter(recyclerViewProductCategoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchViewCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewProductCategoryAdapter.getFilter().filter(newText);
                return false;
            }
        });

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
    private void showProgressDialog(){
        this.progressDialog= new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Getting Data");
        progressDialog.show();
    }

    private void getCategory(){
        showProgressDialog();
        String url = AllUrls.GetCategory+ DataHold.phn;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                parsedata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("number","01700000000");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void parsedata(String response){
        try{
            JSONArray pCategory = new JSONArray(response);
            for(int i =0;i<pCategory.length();i++){
                JSONObject pObject = pCategory.getJSONObject(i);
                ProductItem productItem =new ProductItem();
                productItem.setP_categoryID(pObject.getString("id"));
                productItem.setP_categoryName(pObject.getString("cate"));
                productItem.setP_registeredDate(pObject.getString("date"));
                productItems.add(productItem);

                recyclerViewProductCategoryAdapter = new RecyclerViewProductCategoryAdapter(productItems);
                recyclerView.setAdapter(recyclerViewProductCategoryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                progressDialog.dismiss();

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}