package com.example.apppedidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<ListData> {
    public Adapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context,  R.layout.restaurante_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData=getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.restaurante_item, parent,false);
        }
        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);
        TextView listTime = view.findViewById(R.id.listPrecio);

        listImage.setImageResource(listData.image);
        listName.setText(listData.name);
        listTime.setText(listData.precio);
        return view;
    }
}

