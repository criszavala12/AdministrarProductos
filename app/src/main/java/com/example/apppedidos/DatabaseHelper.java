package com.example.apppedidos;
        import android.annotation.SuppressLint;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper { public static final String DBNAME = "register.db";


    public DatabaseHelper(Context context) {
        super(context, "register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, password TEXT, session_active INTEGER DEFAULT 0)");

        MyDB.execSQL("CREATE TABLE beverages(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, stock INTEGER, image_path TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists beverages");
    }

    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("session_active", 0); // Inicialmente, la sesión se establece como inactiva
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertBeverage(String name, String description, double price, int stock, String imagePath) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("price", price);
        contentValues.put("stock", stock);
        contentValues.put("image_path", imagePath);

        long result = MyDB.insert("beverages", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        } // Devuelve verdadero si la inserción fue exitosa, falso si falló
    }
    public Boolean checkUser(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email = ? and password = ? and session_active = 0", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void updateSessionActive(String email, int sessionActive) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("session_active", sessionActive);
        MyDB.update("users", contentValues, "email = ?", new String[]{email});
        MyDB.close();
    }
    public List<Beverage> getBeverageData() {
        List<Beverage> beverages = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();

        // Especifica las columnas que deseas recuperar en la consulta
        String[] columns = {"name", "price", "image_path"};

        // Realiza la consulta
        Cursor cursor = MyDB.query("beverages", columns, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Lee los datos del cursor y crea objetos Beverage
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    @SuppressLint("Range") String imagePath = cursor.getString(cursor.getColumnIndex("image_path"));

                    Beverage beverage = new Beverage(name, price, imagePath);
                    beverages.add(beverage);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return beverages;
    }
}