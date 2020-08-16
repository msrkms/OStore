package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.Adepter.ProductSellRecyclerAdepter;
import com.atlassoftwarepark.ostore.Adepter.QuickSellItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProductSellAdapter;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewQuickSellAdapter;
import com.atlassoftwarepark.ostore.Adepter.SelectedProductItem;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.atlassoftwarepark.ostore.Object.Category;
import com.atlassoftwarepark.ostore.Object.Customer;
import com.atlassoftwarepark.ostore.Object.Product;
import com.atlassoftwarepark.ostore.Object.ProductSell;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuickSellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuickSellFragment extends Fragment {

    Spinner spinnerProductCategorySelect;
    MaterialButton qSellListBtn,materialButtonSell;
    ArrayList<QuickSellItem>quickSellItems;
    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<ProductSell> productSells=new ArrayList<ProductSell>();
    private RecyclerView recyclerViewProduct,recyclerViewProductSell;

    ArrayList<SelectedProductItem> selectedProductItems;
    RecyclerViewProductSellAdapter recyclerViewProductsellAdapter;


    private ProductGridAdepter productGridAdepter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuickSellFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuickSellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuickSellFragment newInstance(String param1, String param2) {
        QuickSellFragment fragment = new QuickSellFragment();
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
        if(DataHold.productSellsQsell!=null){
            for (int j=1;j<productSells.size();j++){
                productSells.remove(j);
            }
            DataHold.productSellsQsell=productSells;

        }
        View qSell = inflater.inflate(R.layout.fragment_quick_sell, container, false);
        spinnerProductCategorySelect=(Spinner)qSell.findViewById(R.id.selectQuickSellSpinner);
        qSellListBtn=(MaterialButton)qSell.findViewById(R.id.quickSellListBtn);
        recyclerViewProduct=(RecyclerView) qSell.findViewById(R.id.recylerviewProduct) ;
        recyclerViewProductSell=(RecyclerView) qSell.findViewById(R.id.recyclerViewProductSell);
        materialButtonSell=(MaterialButton) qSell.findViewById(R.id.materialButtonSell);

        ProductSellRecyclerAdepter productSellRecyclerAdepter=new ProductSellRecyclerAdepter(getContext(),productSells);
        recyclerViewProductSell.setAdapter(productSellRecyclerAdepter);
        recyclerViewProductSell.setVisibility(View.GONE);
        ProductSell productSellTitle=new ProductSell();
        productSellTitle.setId(0);
        productSellTitle.setProduct(new Product());
        productSellTitle.setQty(0);
        productSells.add(productSellTitle);

        categories=new ArrayList<Category>();
        products=new ArrayList<Product>();

        getCategoryFromAPI();
        getProductFromAPI();


        quickSellItems = new ArrayList<QuickSellItem>();

        QuickSellItem quickSellItem = new QuickSellItem();
        quickSellItem.setQS_invoiceNo("ইনভয়েস");
        quickSellItem.setQS_sellDate("বিক্রির তারিখ");
        quickSellItem.setQS_amount("টাকার পরিমাণ");
        quickSellItem.setQS_action("অ্যাকশন");

        quickSellItems.add(quickSellItem);

        qSellListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuickSellList();
            }
        });

        recyclerViewProduct.addOnItemTouchListener(new ProductGridAdepter.RecyclerTouchListener(getContext(), recyclerViewProduct, new ProductGridAdepter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(DataHold.productSellsQsell!=null){
                    productSells=DataHold.productSellsQsell;

                }
                recyclerViewProductSell.setVisibility(View.VISIBLE);
                ProductSell productSell=new ProductSell();
                if(productSells.size()>1){
                    boolean result=Search(productSells,products.get(position));
                    if(!result){
                        productSell.setId(productSells.size());
                        productSell.setProduct(products.get(position));
                        productSell.setQty(0);
                        productSells.add(productSell);
                    }
                }else{
                    productSell.setId(1);
                    productSell.setProduct(products.get(position));
                    productSell.setQty(0);
                    productSells.add(productSell);
                }

                DataHold.productSellsQsell=productSells;
                ProductSellRecyclerAdepter productSellRecyclerAdepter=new ProductSellRecyclerAdepter(getContext(),productSells);
                recyclerViewProductSell.setAdapter(productSellRecyclerAdepter);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        spinnerProductCategorySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    String category=categories.get(i-1).getCategory();
                    System.out.println(category);
                    productGridAdepter.getFilter().filter(category);
                }else{
                    getProductFromAPI();
                    setProductsRecyclerView();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        materialButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataHold.productSellsQsell!=null){

                    quickSell();
                }
            }
        });

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

        return qSell;
    }



    private void showQuickSellList(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.quick_sell_list_layout,null);

        ImageButton closeDialog;
        RecyclerView recyclerView;
        closeDialog=(ImageButton)view.findViewById(R.id.closeDialogQuickSell);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerViewQuickSellList);


        RecyclerViewQuickSellAdapter recyclerViewQuickSellAdapter=new RecyclerViewQuickSellAdapter(getContext(),quickSellItems);
        recyclerView.setAdapter(recyclerViewQuickSellAdapter);
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
                product.setUnitPrice(jsonObject.getInt("unitprice"));
                products.add(product);


            }

            setProductsRecyclerView();

        }catch (JSONException e){
            e.printStackTrace();
        }
    }



    private void setCategorySpinner(){
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        spinnerProductCategorySelect.setAdapter(arrayAdapterCategory);
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        for (int i=0;i<categories.size();i++){
            stringsCategory.add(categories.get(i).getCategory());
        }

        return stringsCategory;

    }

    private void setProductsRecyclerView(){
        productGridAdepter=new ProductGridAdepter(getContext(),products);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewProduct.setAdapter(productGridAdepter);
    }


    private boolean Search(ArrayList<ProductSell> productSells,Product product){
        boolean search=false;
        for (int i=0;i<productSells.size();i++){
            if(productSells.get(i).getProduct().getId()==product.getId()){
                search=true;
            }
        }
        return search;
    }

    private void quickSell(){

        final RequestQueue LoginQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AllUrls.QuickSell,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int data=Character.getNumericValue(response.charAt(1));
                        if(data==1){
                            new MaterialAlertDialogBuilder(getContext())
                                    .setTitle("Success")
                                    .setMessage(" Quick Sell Completed")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new QuickSellFragment()).commit();
                                           /* for (int j=1;j<productSells.size();j++){
                                                productSells.remove(j);
                                            }
                                            DataHold.productSellsQsell=productSells;
                                            ProductSellRecyclerAdepter productSellRecyclerAdepter=new ProductSellRecyclerAdepter(getContext(),productSells);
                                            recyclerViewProductSell.setAdapter(productSellRecyclerAdepter);*/
                                        }
                                    })
                                    .show();
                        }else{
                            new MaterialAlertDialogBuilder(getContext())
                                    .setTitle("Warning")
                                    .setMessage(" Error")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("warning")
                        .setMessage("Server Down")
                        .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                int counter=DataHold.productSellsQsell.size()-1;
                params.put("counter",String.valueOf(counter));
                params.put("user",String.valueOf(DataHold.phn));
                for (int i=1;i<counter+1;i++){
                    String n="n"+(i);
                    params.put(n,String.valueOf(DataHold.productSellsQsell.get(i).getProduct().getProductName()));
                    String v="v"+(i);
                    params.put(v,String.valueOf(DataHold.productSellsQsell.get(i).getProduct().getUnitPrice()));
                    String q="q"+(i);
                    params.put(q,String.valueOf(DataHold.productSellsQsell.get(i).getQty()));
                    String id="i"+(i);
                    params.put(id,String.valueOf(DataHold.productSellsQsell.get(i).getProduct().getId()));
                }
                System.out.println(params.toString());
                return params;
            }
        };

        LoginQueue.add(stringRequest);

    }
}