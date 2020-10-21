package com.poblete.practica_maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck== PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }


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


        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLatitud.setText("");
                txtLong.setText("");
                txtNombre.setText("");

            }
        });


    }
}