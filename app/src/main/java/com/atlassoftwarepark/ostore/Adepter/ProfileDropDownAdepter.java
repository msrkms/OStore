package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;

public class ProfileDropDownAdepter extends ArrayAdapter {
    public ProfileDropDownAdepter(@NonNull Context context, ArrayList<CustomProfileSpinnerItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_custom_spinner_layout, parent, false);
        }
        CustomProfileSpinnerItem item = (CustomProfileSpinnerItem) getItem(position);
        ImageView spinnerIV = convertView.findViewById(R.id.ivSpinnerLayout);
        if (item != null) {
            spinnerIV.setImageResource(item.getSpinnerItemImage());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_spiner_layout, parent, false);
        }
        CustomProfileSpinnerItem item = (CustomProfileSpinnerItem) getItem(position);
        ImageView dropDownIV = convertView.findViewById(R.id.ivDropDownLayout);
        TextView dropDownTV = convertView.findViewById(R.id.tvDropDownLayout);
        if (item != null) {
            dropDownIV.setImageResource(item.getSpinnerItemImage());
            dropDownTV.setText(item.getSpinnerItemName());
        }
        return convertView;
    }
}
