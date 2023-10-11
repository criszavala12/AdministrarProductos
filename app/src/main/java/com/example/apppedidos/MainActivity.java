package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ImageButton opc,opc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opc=findViewById(R.id.Opcion1);
        opc.setOnClickListener((v)->startActivity(new Intent(MainActivity.this,RestauranteActivity.class)) );

        opc2=findViewById(R.id.Opcion2);
        opc2.setOnClickListener((v)->startActivity(new Intent(MainActivity.this,BebidasActivity.class)) );


    }


}