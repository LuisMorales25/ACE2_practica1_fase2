package com.example.myapplication;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker MarkerPrueba;
    ArrayList<Marker> Markers = new ArrayList<Marker>();
    ArrayList<Double> Latitudes;
    ArrayList<Double> Longitudes;
    ArrayList<Double> uvMaxs;
    ArrayList<Double> uvMins;
    ArrayList<Double> uvMedios;
    ArrayList<Double> mq7Maxs;
    ArrayList<Double> mq7Mins;
    ArrayList<Double> mq7Medios;
    ArrayList<Double> mq135Maxs;
    ArrayList<Double> mq135Mins;
    ArrayList<Double> mq135Medios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Latitudes = (ArrayList<Double>) getIntent().getSerializableExtra("listaLatitud");
        Longitudes = (ArrayList<Double>) getIntent().getSerializableExtra("listaLongitud");

        uvMaxs = (ArrayList<Double>) getIntent().getSerializableExtra("listauvMax");
        uvMins = (ArrayList<Double>) getIntent().getSerializableExtra("listauvMin");
        uvMedios = (ArrayList<Double>) getIntent().getSerializableExtra("listauvMedio");

        mq7Maxs = (ArrayList<Double>) getIntent().getSerializableExtra("listamq7Max");
        mq7Mins = (ArrayList<Double>) getIntent().getSerializableExtra("listamq7Min");
        mq7Medios = (ArrayList<Double>) getIntent().getSerializableExtra("listamq7Medio");

        mq135Maxs = (ArrayList<Double>) getIntent().getSerializableExtra("listamq135Max");
        mq135Mins = (ArrayList<Double>) getIntent().getSerializableExtra("listamq135Min");
        mq135Medios = (ArrayList<Double>) getIntent().getSerializableExtra("listamq135Medio");
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
        LatLng ubicacion, ubicacioninicio;
        ubicacion = new LatLng(Latitudes.get(0), Longitudes.get(0));
        MarkerPrueba = googleMap.addMarker(new MarkerOptions().position(ubicacion).title("Lugar " + 1).snippet("Uv: " + " Mq7: " + "Mq135: ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cloudysunny)));
        Markers.add(MarkerPrueba);
        for (int i = 1; i < Latitudes.size(); i++) {
            ubicacion = new LatLng(Latitudes.get(i), Longitudes.get(i));
            MarkerPrueba = googleMap.addMarker(new MarkerOptions().position(ubicacion).title("Lugar " + (i + 1)).snippet("Uv: " + " Mq7: " + "Mq135: ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cloudysunny)));

            Markers.add(MarkerPrueba);
        }

        //Camara del marcador para posicionarlo en la primera ubicacion
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 10));

        //Click en el marcador para poder generar el evento y mostrar la información correspondiente
        googleMap.setOnMarkerClickListener(this);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        for (int i = 0; i < Markers.size(); i++) {
            if (marker.equals(Markers.get(i))) {
                String latitud, longitud,uvmedio;
                latitud = Double.toString(marker.getPosition().latitude);
                longitud = Double.toString(marker.getPosition().longitude);

                Toast.makeText(this, " Latitud: " + latitud + "\n Longitud: " + longitud + "\n ------------------------------" +
                        "\n Uv Promedio: " + uvMedios.get(i) + "\n Uv Maximo : " + uvMaxs.get(i) + "\n Uv Minimo: " + uvMins.get(i) + "\n ------------------------------" +
                        "\n Mq7 Promedio: " + mq7Medios.get(i) + "\n Mq7 Maximo : " + mq7Maxs.get(i) + "\n Mq7 Minimo: " + mq7Mins.get(i) + "\n ------------------------------" +
                        "\n Mq135 Promedio: " + mq135Maxs.get(i) + "\n Mq135 Maximo : " + mq135Maxs.get(i) + "\n Mq135 Minimo: " + mq135Mins.get(i), Toast.LENGTH_SHORT).show();
            }

        }
        return false;
    }
}