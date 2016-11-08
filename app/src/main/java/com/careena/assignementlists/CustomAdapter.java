package com.careena.assignementlists;


import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Careena on 9/27/16.
 */
public class CustomAdapter extends ArrayAdapter<toDoBean> {

    Context context;

    public CustomAdapter(Context context,  ArrayList<toDoBean> names) {
        super(context, R.layout.double_row, names);
        this.context = context;
    }


    public void remove(int position){
        remove(getItem(position));
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater viewInflater = LayoutInflater.from(getContext());
        View customView = viewInflater.inflate(R.layout.double_row, parent, false);


        //get reference to each toDoItem
        toDoBean nameItem = getItem(position); //each item position in list


        TextView titleView = (TextView) customView.findViewById(R.id.title);
        TextView descView = (TextView) customView.findViewById(R.id.desc);
        final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.checkBox);



        titleView.setText(nameItem.getTitle());
        descView.setText(nameItem.getDesc());




        if (position % 2 == 1) {
            customView.setBackgroundColor(context.getResources().getColor(R.color.LightGoldenrodYellow));
            //titleView.setTextColor();

        } else {
            customView.setBackgroundColor(context.getResources().getColor(R.color.PeachPuff));
        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()){
                   // remove(position);
                    System.out.println("403 ---- check box clicked");
                }
            }
        });



        return customView;
    }



}
