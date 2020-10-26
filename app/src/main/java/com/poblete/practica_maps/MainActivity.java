package com.poblete.practica_maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    EditText txtLatitud, txtLong, txtNombre;
    Button btnMaps, btnLimpiar;
    FusedLocationProviderClient fused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLong = findViewById(R.id.txtLong);
        txtNombre = findViewById(R.id.txtNombre);
        btnMaps = findViewById(R.id.btnMaps);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        //Inicializando el objeto fusedLocation
        fused = LocationServices.getFusedLocationProviderClient(MainActivity.this);


        txtLatitud.setEnabled(false);
        txtLong.setEnabled(false);

        obtenerCoordenadas();

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float lat, lon;
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

    //Método que obtendra las coordenadas
    public void obtenerCoordenadas() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    // En este caso se programan acciones
                    txtLatitud.setText(String.valueOf(location.getLatitude()));
                    txtLong.setText(location.getLongitude()+"");
                }
            }
        });
    }
}