package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.Adepter.ProductSellRecyclerAdepter;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProductSellAdapter;
import com.atlassoftwarepark.ostore.Adepter.RecylerViewProductSellAdepter;
import com.atlassoftwarepark.ostore.Adepter.SelectedProductItem;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.atlassoftwarepark.ostore.Object.Category;
import com.atlassoftwarepark.ostore.Object.Customer;
import com.atlassoftwarepark.ostore.Object.Product;
import com.atlassoftwarepark.ostore.Object.ProductSell;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellFragment extends Fragment {

    private Spinner spinnerCategory,spinnerTimeType,spinnerCustomer,spinnersellType;
    private HorizontalScrollView horizontalScrollViewInstallment;
    private MaterialButton materialButtonSell;
    private MaterialTextView materialAddNewCustomer;
    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private RecyclerView recyclerViewProduct,recyclerViewProductSell;
    private ArrayList<ProductSell> productSells=new ArrayList<ProductSell>();
    //new ArrayList
    ArrayList<SelectedProductItem> selectedProductItems;
    RecyclerViewProductSellAdapter recyclerViewProductsellAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellFragment newInstance(String param1, String param2) {
        SellFragment fragment = new SellFragment();
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
        View viewSell= inflater.inflate(R.layout.fragment_sell, container, false);
        spinnerCategory=(Spinner) viewSell.findViewById(R.id.spinnerAllProduct);
        materialAddNewCustomer=(MaterialTextView)viewSell.findViewById(R.id.materialTextViewAddNewCustomer);
        recyclerViewProduct=(RecyclerView) viewSell.findViewById(R.id.recylerviewProduct) ;
        recyclerViewProductSell=(RecyclerView) viewSell.findViewById(R.id.recyclerViewProductSell);

        ProductSellRecyclerAdepter productSellRecyclerAdepter=new ProductSellRecyclerAdepter(getContext(),productSells);
        recyclerViewProductSell.setAdapter(productSellRecyclerAdepter);


        recyclerViewProduct.addOnItemTouchListener(new ProductGridAdepter.RecyclerTouchListener(getContext(), recyclerViewProductSell, new ProductGridAdepter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                ProductSell productSell=new ProductSell();
                productSell.setId(0);
                productSell.setProduct(products.get(position));
                productSell.setQty(0);
                productSells.add(productSell);

                ProductSellRecyclerAdepter productSellRecyclerAdepter=new ProductSellRecyclerAdepter(getContext(),productSells);
                recyclerViewProductSell.setAdapter(productSellRecyclerAdepter);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        materialAddNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        recyclerViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(DataHold.position);
            }
        });


        categories=new ArrayList<Category>();
        products=new ArrayList<Product>();
        customers =new ArrayList<Customer>();

        getCategoryFromAPI();
        getProductFromAPI();
        getCustomerFromAPI();
        spinnerTimeType=(Spinner) viewSell.findViewById(R.id.spinnerinterestTimeType);
        ArrayAdapter<String> arrayAdapterTimeType=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getTimeType()
        );
        spinnerTimeType.setAdapter(arrayAdapterTimeType);

        spinnerCustomer=(Spinner) viewSell.findViewById(R.id.spinnerCustomers);


        spinnersellType=(Spinner) viewSell.findViewById(R.id.spinnerSellType);
        ArrayAdapter<String> arrayAdapterSellType=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getsellType()
        );
        spinnersellType.setAdapter(arrayAdapterSellType);

        horizontalScrollViewInstallment=(HorizontalScrollView) viewSell.findViewById(R.id.installmentLayout);
        materialButtonSell=(MaterialButton) viewSell.findViewById(R.id.materialButtonSell) ;

        spinnersellType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    horizontalScrollViewInstallment.setVisibility(View.GONE);
                    materialButtonSell.setText("নগদ বিক্রি");
                } else if(i==1){
                    horizontalScrollViewInstallment.setVisibility(View.VISIBLE);
                    materialButtonSell.setText("কিস্তিতে বিক্রি");
                }
                else if(i==2){
                    horizontalScrollViewInstallment.setVisibility(View.GONE);
                    materialButtonSell.setText("বাকিতে বিক্রি");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //selected product item
        selectedProductItems = new ArrayList<SelectedProductItem>();
        SelectedProductItem selectedProductItem = new SelectedProductItem();
        selectedProductItem.setS_productSL("1");
        selectedProductItem.setS_productName("Something");
        selectedProductItem.setS_productQuantity("");
        selectedProductItem.setS_productUnitPrice("10");

        selectedProductItems.add(selectedProductItem);

        recyclerViewProductsellAdapter = new RecyclerViewProductSellAdapter(selectedProductItems);
        recyclerViewProductSell.setAdapter(recyclerViewProductsellAdapter);
        recyclerViewProductSell.setHasFixedSize(true);
        recyclerViewProductSell.setLayoutManager(new LinearLayoutManager(getContext()));

        return viewSell;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        for (int i=0;i<categories.size();i++){
            stringsCategory.add(categories.get(i).getCategory());
        }

        return stringsCategory;

    }
    private List<String> getTimeType() {
        List<String> stringsTimeType=new ArrayList<String>();
        stringsTimeType.add("মেয়াদ কাল ধরণ");
        stringsTimeType.add(" দিন");
        stringsTimeType.add("মাস");

        return stringsTimeType;

    }

    private List<String> getsellType() {
        List<String> stringsSellType=new ArrayList<String>();
        stringsSellType.add("নগদ");
        stringsSellType.add("কিস্তি");
        stringsSellType.add("বাকি");

        return stringsSellType;

    }
    private List<String> getCustomer() {

        List<String> cutomer=new ArrayList<String>();

        for (int i=0;i<customers.size();i++){
            cutomer.add(customers.get(i).getName());
        }
        return cutomer;

    }

    private void showDialog(){

        View dialogCustomer = LayoutInflater.from(getContext()).inflate(R.layout.add_new_customer_layout,null);

        ImageButton closeDiaglogThis;
        TextInputEditText customerName,customerPhone,customerAddress;

        customerName=(TextInputEditText)dialogCustomer.findViewById(R.id.textInputEditTextAddCustomerName);
        customerPhone=(TextInputEditText)dialogCustomer.findViewById(R.id.textInputEditTextAddCustomerPhone);
        customerAddress=(TextInputEditText)dialogCustomer.findViewById(R.id.textInputEditTextAddCustomerAddress);
        closeDiaglogThis=(ImageButton)dialogCustomer.findViewById(R.id.closeDialogAddCustomer);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setView(dialogCustomer).create();
        alertDialog.show();

        closeDiaglogThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });



    }


    private void getCategoryFromAPI(){
        String url = AllUrls.GetCategory+ DataHold.phn;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseCategories(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error",error.toString());


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void getProductFromAPI(){
        String url = AllUrls.GetProduct+ DataHold.phn;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseProduct(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error",error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void getCustomerFromAPI(){
        String url = AllUrls.GetCustomer+ DataHold.phn;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseCustomer(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error",error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void parseCustomer(String response){
        try{
            customers=new ArrayList<Customer>();
            JSONArray jsonArrayCustomer = new JSONArray(response);
            for(int i =0;i<jsonArrayCustomer.length();i++){
                JSONObject jsonObject = jsonArrayCustomer.getJSONObject(i);
                Customer customer =new Customer();
                customer.setId(jsonObject.getInt("id"));
                customer.setName(jsonObject.getString("name"));

                customers.add(customer);

            }
            setSpinnerCustomer();


        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseCategories(String response){
        try{
            categories=new ArrayList<Category>();
            JSONArray jsonArrayCategory = new JSONArray(response);
            for(int i =0;i<jsonArrayCategory.length();i++){
                JSONObject jsonObject = jsonArrayCategory.getJSONObject(i);
                Category category= new Category();
                category.setId(jsonObject.getInt("id"));
                category.setCategory(jsonObject.getString("cate"));
                categories.add(category);

            }
            setCategorySpinner();

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseProduct(String response){
        try{
            products=new ArrayList<Product>();
            JSONArray jsonArrayProduct = new JSONArray(response);
            for(int i =0;i<jsonArrayProduct.length();i++){
                JSONObject jsonObject = jsonArrayProduct.getJSONObject(i);
                Product product=new Product();
                product.setId(jsonObject.getInt("id"));
                product.setCategory(jsonObject.getString("group"));
                product.setProductName(jsonObject.getString("productName"));
                product.setStock(jsonObject.getInt("stock"));
                products.add(product);

            }
            setProductsRecyclerView();

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void setProductsRecyclerView(){
        ProductGridAdepter productGridAdepter=new ProductGridAdepter(getContext(),products);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewProduct.setAdapter(productGridAdepter);
    }


    private void setCategorySpinner(){
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        spinnerCategory.setAdapter(arrayAdapterCategory);
    }

    private void setSpinnerCustomer(){
        ArrayAdapter<String> arrayAdapterCustomer=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCustomer()
        );
        spinnerCustomer.setAdapter(arrayAdapterCustomer);
    }




}