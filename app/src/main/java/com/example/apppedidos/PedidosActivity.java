package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class PedidosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BeverageAdapter adapter;
    private List<Beverage> beverages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crea una instancia de DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Obtiene la lista de bebidas desde la base de datos
        beverages = dbHelper.getBeverageData();

        // Crea un adaptador personalizado y asigna la lista de bebidas
        adapter = new BeverageAdapter(beverages);

        // Establece el adaptador en el RecyclerView
        recyclerView.setAdapter(adapter);
    }
}