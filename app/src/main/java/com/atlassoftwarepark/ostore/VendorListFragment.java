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
import android.widget.LinearLayout;

import com.atlassoftwarepark.ostore.Adepter.RecyclerViewVendorAdapter;
import com.atlassoftwarepark.ostore.Adepter.VendorItem;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorListFragment extends Fragment {

    LinearLayout linearAddVendor;
    RecyclerView recyclerView;
    ArrayList<VendorItem> vendorItems;

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
        vendorItem.setVendorName("ভেন্ডর নাম");
        vendorItem.setVendorPhone("ফোন নাম্বার");
        vendorItem.setVendorInstitute("প্রতিষ্ঠানের নাম");
        vendorItem.setVendorAddress("ঠিকানা");
        vendorItem.setVendorAction("অ্যাকশান");

        vendorItems.add(vendorItem);

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
}