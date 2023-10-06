package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PedidosActivity extends AppCompatActivity {
    private EditText nombreClienteEditText;
    private EditText apellidoClienteEditText;
    private EditText direccionEditText;
    private EditText productoEditText;
    private EditText cantidadEditText;
    private Button confirmarPedidoButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        // Asigna las vistas a las variables
        nombreClienteEditText = findViewById(R.id.editTextNombreCliente);
        apellidoClienteEditText = findViewById(R.id.editTextApellidoCliente);
        direccionEditText = findViewById(R.id.editTextDireccion);
        productoEditText = findViewById(R.id.editTextProducto);
        cantidadEditText = findViewById(R.id.editTextCantidad);
        confirmarPedidoButton = findViewById(R.id.btnConfirmarPedido);

        // Configura un Listener para el botón de confirmar pedido
        confirmarPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama a una función para realizar la validación
                if (validarCampos()) {
                    // Todos los campos son válidos, puedes realizar la acción deseada aquí
                    String nombreCliente = nombreClienteEditText.getText().toString();
                    String apellidoCliente = apellidoClienteEditText.getText().toString();
                    String direccion = direccionEditText.getText().toString();
                    String producto = productoEditText.getText().toString();
                    int cantidad = Integer.parseInt(cantidadEditText.getText().toString());

                    // Realiza la acción con los datos ingresados
                }
            }
        });
    }

    // Función para validar los campos
    private boolean validarCampos() {
        String nombreCliente = nombreClienteEditText.getText().toString();
        String apellidoCliente = apellidoClienteEditText.getText().toString();
        String direccion = direccionEditText.getText().toString();
        String producto = productoEditText.getText().toString();
        String cantidad = cantidadEditText.getText().toString();

        if (nombreCliente.isEmpty() && cantidad.isEmpty() && apellidoCliente.isEmpty() && direccion.isEmpty() && producto.isEmpty()) {
            nombreClienteEditText.setError("Campo obligatorio");
            apellidoClienteEditText.setError("Campo obligatorio");
            direccionEditText.setError("Campo obligatorio");
            productoEditText.setError("Campo obligatorio");
            cantidadEditText.setError("Campo obligatorio");
            return false;
        }

        // Puedes agregar más validaciones según tus necesidades

        return true;
    }
}