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

public class NotificationDropDownAdepter extends ArrayAdapter {

    public NotificationDropDownAdepter(@NonNull Context context, ArrayList<NotificationSpinnerItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_custom_spinner, parent, false);
        }
        NotificationSpinnerItem item = (NotificationSpinnerItem) getItem(position);
        ImageView spinnerIV = convertView.findViewById(R.id.ivSpinnerLayout);
        if (item != null) {
            spinnerIV.setImageResource(item.getSpinnerItemImage());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_spiner_layout, parent, false);
        }
        NotificationSpinnerItem item = (NotificationSpinnerItem) getItem(position);
        ImageView dropDownIV = convertView.findViewById(R.id.ivNotification);
        TextView dropDownTVName = convertView.findViewById(R.id.tvName);
        TextView dropDownTvMessage=convertView.findViewById(R.id.tvMessage);
        if (item != null) {
            dropDownIV.setImageResource(item.getSpinnerItemImage());
            dropDownTVName.setText(item.getSpinnerItemName());
            dropDownTvMessage.setText(item.getSpinnerMessage());
        }
        return convertView;
    }
}
