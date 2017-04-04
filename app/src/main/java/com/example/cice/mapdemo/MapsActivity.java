package com.example.cice.mapdemo;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.jar.*;

//Geocoding - String a datos
//ReverseGeocoding - datos a string
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    /**
     * Google Map Api
     *      GoogleMap -> Descargar y mostrar los mapas y responder a map controls
     *      MapView -> Dibuja el objeto GoogleMap
     *      SupportMapFragment -> Permite colocar un mapa dentro de un fragment
     *      Maker -> Marcar localizaciones en el mapa (Long, Lat)
     *          Especificar título, texto, icono, draggable
     *      Shapes -> Dibujar líneas o formas en el mapa
     *          Usa clases
     *              - Polyline
     *              - Polygon
     *              - Circle
     *      UISettings -> Define el uso del zoom, localizacion usuario, controles, brújula
     *      MyLocationlayer -> Un bton para mostrar la posicion actual del usuario
     */


    /**
     * Tipos de mapas
     * - GoogleMap.MAP_TYPE_NONE -> Grid vacío
     * - GoogleMap.MAP_TYPE_NORMAL -> Estándar
     * - GoogleMap.MAP_TYPE_SATELLITE -> Vista por satélite
     * - GoogleMap.MAP_TYPE_HYBRID -> Mix
     * - GoogleMap.MAP_TYPE_TERRAIN -> Info topográfica
     */

    double latitude;
    double longitude;

    List<Address> geocodeMatches = null;

    private String TAG = "Map";
    private String TAG2 = "MapReverse";

    private GoogleMap mMap;

    String Address1;
    String Address2;
    String State;
    String ZipCode;
    String Country;
    UiSettings mapSettings;

    private static final int LOCATION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_REQUEST_CODE);

        mapFragment.getMapAsync(this);

/*        try {
            geocodeMatches = new Geocoder(this).getFromLocationName("Calle Islas Malvinas 44, Getafe", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty()) {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
            Log.i(TAG, "Latitud: " + latitude);
            Log.i(TAG, "Longitud: " + longitude);
        }*/

/*        try {
            geocodeMatches = new Geocoder(this).getFromLocation(40.2821008, -3.7526224, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty()) {
            Address1 = geocodeMatches.get(0).getAddressLine(0);
            Address2 = geocodeMatches.get(0).getAddressLine(1);
            State = geocodeMatches.get(0).getAdminArea();
            ZipCode = geocodeMatches.get(0).getPostalCode();
            Country = geocodeMatches.get(0).getCountryName();

            Log.i(TAG2, "Direccion: " + Address1);
            Log.i(TAG2, "Direccion 2: " + Address2);
            Log.i(TAG2, "Estado: " + State);
            Log.i(TAG2, "CodPostal: " + ZipCode);
            Log.i(TAG2, "Country: " + Country);
        }*/


    }

    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this, permissionType);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //
                }
                return;
        }
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

        //Modificar mapa
        if(mMap != null)
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        //Mostrar controles a los usuarios
        mapSettings = mMap.getUiSettings();
            //Zoom
            //XML: map:uiZoomControls="true"
        mapSettings.setZoomControlsEnabled(true);

            //Brújula
            //XML: map:uiCompass="true"
        mapSettings.setCompassEnabled(true);

            //Gestos
            //XMl: map:uiZoomGestures="true"
        mapSettings.setZoomGesturesEnabled(true);

            //Scrolling / Panning
            //XML: map:uiScrollGestures="true"
        mapSettings.setScrollGesturesEnabled(true);

            //Tilt -> Para ver los edificios en 3D
            //XML: map:uiTiltGestures="true"
        mapSettings.setTiltGesturesEnabled(true);

            //Rotation
            //XML:uiRotateGestures="true"
        mapSettings.setRotateGesturesEnabled(true);


        //Añadir un Marcador
        LatLng MUSEO = new LatLng(40.4137859, -3.6943211);
        Marker museo = mMap.addMarker(new MarkerOptions()
                .position(MUSEO)
                .title("Museo del prado")
                .snippet("Hola hola"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(MUSEO));


        //Mercator Projection
        /**
         * - Target -> Localizacion del centro del mapa
         * - Zoom -> Nivel de zoom
         * - Tilt -> Ángulo de la camara
         * - Bearing -> Orientación del mapa en grados medido en el sentido de las agujas del reloj desde el polo Norte
         *
         *      CameraUpdate -> CameraUpdateFactory
         *
         */

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MUSEO, 15));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MUSEO)
                .zoom(16)
                .bearing(70)
                .tilt(25)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

/*        if (mMap != null) {
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
            mMap.setMyLocationEnabled(true);
        }*/
    }
}
