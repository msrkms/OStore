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

import com.atlassoftwarepark.ostore.Adepter.CustomerItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewCustomerAdapter;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayout linearAddCustomer;
    ArrayList<CustomerItem> customerItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerListFragment newInstance(String param1, String param2) {
        CustomerListFragment fragment = new CustomerListFragment();
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
        View customer = inflater.inflate(R.layout.fragment_customer_list, container, false);

        recyclerView=(RecyclerView)customer.findViewById(R.id.recyclerViewCustomerList);
        linearAddCustomer=(LinearLayout)customer.findViewById(R.id.layoutAddCustomer);

        customerItems=new ArrayList<CustomerItem>();
        CustomerItem customerItem = new CustomerItem();
        customerItem.setCustomerName("কাস্টমার নাম");
        customerItem.setCustomerPhone("ফোন নাম্বার");
        customerItem.setCustomerAddress("ঠিকানা");
        customerItem.setCustomerAction("অ্যাকশান");

        customerItems.add(customerItem);

        RecyclerViewCustomerAdapter recyclerViewCustomerAdapter= new RecyclerViewCustomerAdapter(getContext(),customerItems);
        recyclerView.setAdapter(recyclerViewCustomerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        linearAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return customer;
    }

    private void showDialog(){

        View addCustomer = LayoutInflater.from(getContext()).inflate(R.layout.add_customer_layout,null);
        ImageButton closeDialogCustomer;
        TextInputEditText textInputEditTextCustomerName,textInputEditTextPhoneNumber,textInputEditTextAddress;
        closeDialogCustomer=(ImageButton)addCustomer.findViewById(R.id.closeDialogCustomer);
        textInputEditTextCustomerName=(TextInputEditText)addCustomer.findViewById(R.id.textInputEditTextCustomerName);
        textInputEditTextPhoneNumber=(TextInputEditText)addCustomer.findViewById(R.id.textInputEditTextCustomerPhone);
        textInputEditTextAddress=(TextInputEditText)addCustomer.findViewById(R.id.textInputEditTextCustomerAddress);
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setView(addCustomer).create();

        alertDialog.show();

        closeDialogCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

}