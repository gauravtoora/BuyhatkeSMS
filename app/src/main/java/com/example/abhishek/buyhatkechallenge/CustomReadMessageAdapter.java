package com.example.abhishek.buyhatkechallenge;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gaurav.buyhatkechallenge.R;

import java.util.List;

/**
 * Created by gaurav on 5/12/16.
 */
public class CustomReadMessageAdapter extends ArrayAdapter<SMSData> {

    // List context
    private final Context context;
    // List values
    private final List<SMSData> smsList;

    public CustomReadMessageAdapter(Context context, List<SMSData> smsList) {
        super(context, R.layout.activity_main, smsList);
        this.context = context;
        this.smsList = smsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_read_message,parent,false);
        TextView senderNumber = (TextView) rowView.findViewById(R.id.tv_number);
        senderNumber.setText(smsList.get(position).getNumber());
        return rowView;
    }

}
