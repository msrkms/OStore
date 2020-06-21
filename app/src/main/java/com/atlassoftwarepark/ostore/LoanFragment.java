package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.atlassoftwarepark.ostore.Adepter.LoanItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewLoanAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoanFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<LoanItem> loanItems;
    LinearLayout linearLayoutAddBorrow;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoanFragment newInstance(String param1, String param2) {
        LoanFragment fragment = new LoanFragment();
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
        View loanView = inflater.inflate(R.layout.fragment_loan, container, false);

        recyclerView=(RecyclerView)loanView.findViewById(R.id.recyclerLoanReport);
        linearLayoutAddBorrow=(LinearLayout)loanView.findViewById(R.id.layoutAddBorrow);

        linearLayoutAddBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        loanItems=new ArrayList<LoanItem>();
        LoanItem loanItem=new LoanItem();
        loanItem.setDate("তারিখ");
        loanItem.setAmount("টাকার পরিমাণ");
        loanItem.setStatus("স্ট্যাটাস");
        loanItem.setMarfot("মারফৎ");
        loanItem.setAction("অ্যাকশন");

        loanItems.add(loanItem);

        RecyclerViewLoanAdapter recyclerViewLoanAdapter=new RecyclerViewLoanAdapter(getContext(),loanItems);
        recyclerView.setAdapter(recyclerViewLoanAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return loanView;
    }
    private void showDialog(){
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.layout_add_borrow);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView loanDate;
        Spinner categorySpinner;
        final TextInputEditText loadDateEditText;
        loanDate=(ImageView)dialog.findViewById(R.id.loanDateSelect);
        loadDateEditText=(TextInputEditText)dialog.findViewById(R.id.textInputEditTextLoanDate) ;
        categorySpinner=(Spinner)dialog.findViewById(R.id.spinnerLoanCategory);


        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        categorySpinner.setAdapter(arrayAdapterCategory);


        loanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select a Date");
                final MaterialDatePicker materialDatePickerLoanDate=builder.build();
                materialDatePickerLoanDate.show(getFragmentManager(),"Date Picker");

                materialDatePickerLoanDate.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        loadDateEditText.setText(materialDatePickerLoanDate.getHeaderText());
                    }
                });

            }
        });
        dialog.show();
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("ঋণদান");
        stringsCategory.add("ঋণগ্রহন");

        return stringsCategory;

    }

}