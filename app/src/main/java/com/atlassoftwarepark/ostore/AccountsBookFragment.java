package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsBookFragment extends Fragment {

    TextInputEditText startDate,endDate;
    ImageView imageViewStart,imageViewEnd;
    MaterialButton materialButtonViewDetails;
    CardView cardViewDetails;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountsBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountsBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountsBookFragment newInstance(String param1, String param2) {
        AccountsBookFragment fragment = new AccountsBookFragment();
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
        View viewAccountBook= inflater.inflate(R.layout.fragment_accounts_book, container, false);

        startDate=(TextInputEditText)viewAccountBook.findViewById(R.id.textInputEditTextStartDate);
        endDate=(TextInputEditText)viewAccountBook.findViewById(R.id.textInputEditTextEndDate);
        imageViewStart=(ImageView)viewAccountBook.findViewById(R.id.startDateSelect);
        imageViewEnd=(ImageView)viewAccountBook.findViewById(R.id.endDateSelect);
        materialButtonViewDetails=(MaterialButton) viewAccountBook.findViewById(R.id.materialButtonShowDetails);
        cardViewDetails=(CardView) viewAccountBook.findViewById(R.id.cardViewAllDeatails);
        cardViewDetails.setVisibility(View.GONE);
        MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        final MaterialDatePicker materialDatePickerstart=builder.build();
        final MaterialDatePicker materialDatePickerend=builder.build();


        imageViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePickerstart.show(getFragmentManager(),"Date Picker");

                materialDatePickerstart.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startDate.setText(materialDatePickerstart.getHeaderText());
                    }
                });
            }
        });



        imageViewEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePickerend.show(getFragmentManager(),"Date Picker");

                materialDatePickerend.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endDate.setText(materialDatePickerend.getHeaderText());
                    }
                });

            }
        });

        materialButtonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDetails.setVisibility(View.VISIBLE);
            }
        });


        return viewAccountBook;
    }
}