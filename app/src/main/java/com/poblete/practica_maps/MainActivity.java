package com.poblete.practica_maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText txtLatitud, txtLong, txtNombre;
    Button btnMaps, btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLong = findViewById(R.id.txtLong);
        txtNombre = findViewById(R.id.txtNombre);
        btnMaps = findViewById(R.id.btnMaps);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float lat ,lon;
                String nombre;

                lat = Float.parseFloat(txtLatitud.getText().toString());
                lon = Float.parseFloat(txtLong.getText().toString());
                nombre = txtNombre.getText().toString();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Latitud", lat);
                intent.putExtra("Longitud", lon);
                intent.putExtra("Nombre", nombre);
                startActivity(intent);

            }
        });


    }
}