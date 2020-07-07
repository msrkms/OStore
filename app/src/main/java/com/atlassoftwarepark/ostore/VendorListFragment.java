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
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewVendorAdapter;
import com.atlassoftwarepark.ostore.Adepter.VendorItem;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorListFragment extends Fragment {

    LinearLayout linearAddVendor;
    RecyclerView recyclerView;
    ArrayList<VendorItem> vendorItems;
    ProgressDialog progressDialog;
    String vendorHolder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VendorListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VendorListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VendorListFragment newInstance(String param1, String param2) {
        VendorListFragment fragment = new VendorListFragment();
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
        View vendor = inflater.inflate(R.layout.fragment_vendor_list, container, false);
        linearAddVendor=(LinearLayout)vendor.findViewById(R.id.layoutAddVendor);
        recyclerView=(RecyclerView)vendor.findViewById(R.id.recyclerViewVendorList);

        vendorItems=new ArrayList<VendorItem>();
        VendorItem vendorItem=new VendorItem();
        vendorItem.setVendorId("#");
        vendorItem.setVendorName("ভেন্ডর নাম");
        vendorItem.setVendorPhone("ফোন নাম্বার");
        vendorItem.setVendorInstitute("প্রতিষ্ঠানের নাম");
        vendorItem.setVendorAddress("ঠিকানা");
        vendorItem.setVendorAction("অ্যাকশান");

        vendorItems.add(vendorItem);


        getVendor();




        RecyclerViewVendorAdapter recyclerViewVendorAdapter = new RecyclerViewVendorAdapter(getContext(),vendorItems);
        recyclerView.setAdapter(recyclerViewVendorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        linearAddVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return vendor;
    }

    private void getVendor(){
        showProgressDialog();
        String url = AllUrls.GetVendor+ DataHold.phn;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                parsedata(response);
                vendorHolder=response;
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
            JSONArray vendor = new JSONArray(response);
            for(int i =0;i<vendor.length();i++){
                JSONObject vObject = vendor.getJSONObject(i);
                VendorItem vendorItem1=new VendorItem();
                vendorItem1.setVendorId(vObject.getString("id"));
                vendorItem1.setVendorName(vObject.getString("name"));
                vendorItem1.setVendorInstitute(vObject.getString("org"));
                vendorItem1.setVendorPhone(vObject.getString("phone"));
                vendorItem1.setVendorAddress(vObject.getString("address"));
                vendorItems.add(vendorItem1);

                RecyclerViewVendorAdapter recyclerViewVendorAdapter = new RecyclerViewVendorAdapter(getContext(),vendorItems);
                recyclerView.setAdapter(recyclerViewVendorAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                progressDialog.dismiss();

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void showDialog(){

        View addVendor = LayoutInflater.from(getContext()).inflate(R.layout.add_new_vendor_layout,null);
        ImageButton closeDialog;
        TextInputEditText textInputEditTextName,textInputEditTextPhone,textInputEditTextInstitute,textInputEditTextAddress;
        closeDialog = (ImageButton)addVendor.findViewById(R.id.closeDialogVendor);
        textInputEditTextName=(TextInputEditText)addVendor.findViewById(R.id.textInputEditTextVendorName);
        textInputEditTextInstitute=(TextInputEditText)addVendor.findViewById(R.id.textInputEditTextVendorInstitute);
        textInputEditTextPhone=(TextInputEditText)addVendor.findViewById(R.id.textInputEditTextVendorPhone);
        textInputEditTextAddress=(TextInputEditText)addVendor.findViewById(R.id.textInputEditTextVendorAddress);


        final AlertDialog builder = new AlertDialog.Builder(getContext()).setView(addVendor).create();

        builder.show();

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });



    }

    private void showProgressDialog(){
        this.progressDialog= new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Getting Data");
        progressDialog.show();
    }
}