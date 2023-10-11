package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbHelper = new DatabaseHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hasStoredCredentials()) {
                    // El usuario tiene credenciales almacenadas, llevarlo a la actividad principal
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    // El usuario no tiene credenciales almacenadas, llevarlo a la actividad de inicio de sesión
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 1000); // Retraso de 1 segundo para mostrar la pantalla de bienvenida (splash screen)
    }

    private boolean hasStoredCredentials() {
        // Aquí debes implementar la lógica para verificar si existen credenciales de usuario en la base de datos SQLite
        // y si el estado de sesión (session_active) es igual a 1 (sesión activa).

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE session_active = 1", null);
        boolean hasActiveSession = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return hasActiveSession;
    }

}