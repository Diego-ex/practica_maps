package com.poblete.practica_maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private float latitud, longitud;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        latitud = bundle.getFloat("Latitud");
        longitud = bundle.getFloat("Longitud");
        nombre = bundle.getString("Nombre");



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latEstatica = -36.827238;
        double lonEstatica = -73.05024;
        String tituloEstatica = "Plaza Concepción";
        LatLng plaza = new LatLng(latEstatica,lonEstatica);


        // Add a marker in Sydney and move the camera
        LatLng lugar = new LatLng(this.latitud,this.longitud);
        mMap.addMarker(new MarkerOptions().position(lugar).title(this.nombre));

        mMap.addMarker(new MarkerOptions().position(plaza).title(tituloEstatica));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar, 17));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}