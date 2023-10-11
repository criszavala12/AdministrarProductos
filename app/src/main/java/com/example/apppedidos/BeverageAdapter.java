package com.example.apppedidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.BeverageViewHolder> {
    private List<Beverage> beverages;
    private Context context;

    public BeverageAdapter(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    @Override
    public BeverageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.restaurante_item, parent, false);
        return new BeverageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeverageViewHolder holder, int position) {
        Beverage beverage = beverages.get(position);

        // Configura las vistas con los datos de la bebida
        holder.nombreTextView.setText(beverage.getName());
        holder.precioTextView.setText(String.valueOf(beverage.getPrice()));

        // Cargar la imagen usando Glide u otra biblioteca
        Glide.with(context).load(beverage.getImagePath()).into(holder.imagenImageView);
    }

    @Override
    public int getItemCount() {
        return beverages.size();
    }

    public class BeverageViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenImageView;
        TextView nombreTextView;
        TextView precioTextView;

        public BeverageViewHolder(View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.listImage);
            nombreTextView = itemView.findViewById(R.id.listName);
            precioTextView = itemView.findViewById(R.id.listPrecio);
        }
    }
}