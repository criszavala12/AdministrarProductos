package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BebidasActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;  // Para almacenar la URI de la imagen seleccionada

    private DatabaseHelper dbHelper;

    private EditText nameEditText;
    ImageButton saveButton;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private EditText stockEditText;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);

        dbHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.notes_title_text);
        descriptionEditText = findViewById(R.id.notes_content_text);
        priceEditText = findViewById(R.id.notes_precio);
        stockEditText = findViewById(R.id.notesa);


        Button uploadImageButton = findViewById(R.id.upload_image_button);
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

      saveButton = findViewById(R.id.save_note_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBeverage();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            ImageView selectedImageView = findViewById(R.id.selected_image);
            selectedImageView.setVisibility(View.VISIBLE);

            Glide.with(this)
                    .load(imageUri)
                    .into(selectedImageView);
        }
            // Aquí puedes cargar y mostrar la imagen seleccionada, por ejemplo, en un ImageView.
            // Si deseas guardar la URI de la imagen en la base de datos, puedes hacerlo en el método
            // donde insertas los detalles de la bebida en la base de datos.
        }
    private void saveBeverage() {
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String priceStr = priceEditText.getText().toString().trim();
        String stockStr = stockEditText.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty() || imageUri == null) {
            // Realiza la validación de campos obligatorios y la existencia de una imagen
            Toast.makeText(this, "Por favor, complete todos los campos y seleccione una imagen.", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        // Convierte la Uri de la imagen en una cadena
        String imageUriString = imageUri.toString();

        // Insertar los datos en la base de datos
        boolean isInserted = dbHelper.insertBeverage(name, description, price, stock, imageUriString);

        if (isInserted) {
            Toast.makeText(this, "Bebida guardada exitosamente", Toast.LENGTH_SHORT).show();
            // Puedes agregar código para limpiar los campos después de la inserción
            nameEditText.setText("");
            descriptionEditText.setText("");
            priceEditText.setText("");
            stockEditText.setText("");
        } else {
            Toast.makeText(this, "Error al guardar la bebida", Toast.LENGTH_SHORT).show();
        }
    }
}