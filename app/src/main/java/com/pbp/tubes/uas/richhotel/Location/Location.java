package com.pbp.tubes.uas.richhotel.Location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.pbp.tubes.uas.richhotel.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

public class Location extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    private static final String DESTINATION_SYMBOL_LAYER_ID ="destination-symbol-layer-id";
    private static final String DESTINATION_ICON_ID ="destination-icon-id";
    private static final String DESTINATION_SOURCE_ID ="destination-source-id";
    private static final int REQUEST_CODE_AUTOCOMPLETE=1;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;
    private FloatingActionButton hotel_loc, current_loc, navigate_loc;
    private DirectionsRoute currentRoute;
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private MapView mapView;
    private Spinner dropdown;
    private Point origin;
    private final Point destination= Point.fromLngLat(110.416138, -7.779417);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.location);

        mapView= findViewById(R.id.mapView);
        hotel_loc= findViewById(R.id.fab_location_hotel);
        current_loc= findViewById(R.id.fab_location_current);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        navigate_loc = findViewById(R.id.fab_navigation);
        final TextView textTitle = findViewById(R.id.title);
        hotel_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
                symbolLayerIconFeatureList.add(Feature.fromGeometry(destination));
                GeoJsonSource source = mapboxMap.getStyle().getSourceAs(DESTINATION_SOURCE_ID);
                source.setGeoJson(FeatureCollection.fromFeatures(symbolLayerIconFeatureList));
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        new CameraPosition.Builder()
                                .target(new LatLng(destination.latitude(), destination.longitude()))
                                .zoom(17)
                                .build()), 4000);
            }
        });

        current_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        new CameraPosition.Builder()
                                .target(new LatLng(origin.latitude(), origin.longitude()))
                                .zoom(15)
                                .build()), 4000);
            }
        });

        navigate_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean simulateRoute = true;
                NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .directionsRoute(currentRoute)
                        .shouldSimulateRoute(simulateRoute)
                        .build();
                NavigationLauncher.startNavigation(Location.this, options);
            }
        });
    }

    private void initLayers(@NonNull Style loadedMapStyle){
        loadedMapStyle.addImage(DESTINATION_ICON_ID,
                BitmapFactory.decodeResource(this.getResources(), R.drawable.mapbox_marker_icon_default));
        GeoJsonSource geoJsonSource = new GeoJsonSource(DESTINATION_SOURCE_ID);
        loadedMapStyle.addSource(geoJsonSource);
        SymbolLayer destinationSymbolLayer = new SymbolLayer(DESTINATION_SYMBOL_LAYER_ID, DESTINATION_SOURCE_ID);
        destinationSymbolLayer.withProperties(
                iconImage(DESTINATION_ICON_ID),
                iconAllowOverlap(true),
                iconIgnorePlacement(true)
        );
        loadedMapStyle.addLayer(destinationSymbolLayer);
    }

    @SuppressLint("MissingPermission")
    private void enableLocationComponent(@NonNull Style loadedMapStyle){
        if(PermissionsManager.areLocationPermissionsGranted(this)) {
            try {
                LocationComponent locationComponent = mapboxMap.getLocationComponent();
                locationComponent.activateLocationComponent(
                        LocationComponentActivationOptions.builder(this, loadedMapStyle).build()
                );
                locationComponent.setLocationComponentEnabled(true);

                locationComponent.setCameraMode(CameraMode.TRACKING);
                locationComponent.setRenderMode(RenderMode.COMPASS);


                this.origin = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                        locationComponent.getLastKnownLocation().getLatitude());
            }catch (Exception e){
                Toast.makeText(this, "Location error!", Toast.LENGTH_SHORT).show();
            }

        }else{
            permissionsManager = new PermissionsManager(Location.this);
            permissionsManager.requestLocationPermissions(Location.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        permissionsManager.onRequestPermissionsResult(requestCode, permission, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Grant Location Permission", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if(granted){
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        }else{
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;


        mapboxMap.setStyle(new Style.Builder().fromUri(Style.MAPBOX_STREETS),
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
                        enableLocationComponent(style);

                        initLayers(style);
                        getRoute(origin, destination);

                        navigate_loc.setEnabled(true);
                        navigate_loc.setBackgroundResource(R.color.mapbox_blue);
                        return;

                    }



                });
    }

    private void getRoute(Point origin, Point destination) {
        Context context = getApplicationContext();
        NavigationRoute.builder(context)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.e(TAG, "No route found, check right user and access token");
                            return;
                        } else if (response.body().routes().size() == 0) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);

                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        }else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap);
                        }

                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Log.e(TAG, "Error" + t.getMessage());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode== REQUEST_CODE_AUTOCOMPLETE){
            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);

            navigate_loc.setEnabled(true);
            navigate_loc.setBackgroundResource(R.color.mapbox_blue);
            getRoute(origin, destination);
            if (mapboxMap != null){
                Style style = mapboxMap.getStyle();
                if (style!=null){
                    GeoJsonSource source = style.getSourceAs("geojsonSourceLayerId");
                    if(source!=null){
                        source.setGeoJson(FeatureCollection.fromFeatures(
                                new Feature[]{Feature.fromJson(selectedCarmenFeature.toJson())}
                        ));
                    }

                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(),
                                            ((Point) selectedCarmenFeature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
