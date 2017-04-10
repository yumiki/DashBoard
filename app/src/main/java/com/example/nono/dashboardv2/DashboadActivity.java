package com.example.nono.dashboardv2;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nono.dashboardv2.data.Velib;
import com.example.nono.dashboardv2.util.ApiManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboadActivity extends AppCompatActivity implements MapTileFragment.OnFragmentInteractionListener, LocationListener {
    private LocationManager locationManager;
    private Location location;
    View velibTile;
    StationAdapter stationAdapter;
    List<Velib.Station> stationsData;
    Button mButton;
    MapTileFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);
        velibTile = findViewById(R.id.velibTile);
        RecyclerView velibStationView = (RecyclerView) velibTile.findViewById(R.id.velibRv);

        stationsData = new ArrayList<>();

        velibStationView.setLayoutManager(new LinearLayoutManager(this));

        stationAdapter = new StationAdapter(stationsData, getBaseContext());
        velibStationView.setAdapter(stationAdapter);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mapTile, new MapTileFragment(), "MapTile")
                    .addToBackStack("MapTile")
                    .commit();
        }
        getSupportFragmentManager().executePendingTransactions();

        frag = (MapTileFragment) getSupportFragmentManager().findFragmentByTag("MapTile");


        //MapTileFragment frag = (MapTileFragment) getSupportFragmentManager().findFragmentByTag("MapTile");

        //if(frag == null)
        //   Snackbar.make(velibTile,"Il y a un problème 1",Snackbar.LENGTH_INDEFINITE).show();


        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(view -> {
            Toast.makeText(getBaseContext(), "Blabal", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        ApiManager.apiManagerDist.getVelib(
                "stations-velib-disponibilites-en-temps-reel",
                "20 Rue Guillaume Bertrand",
                Arrays.asList("banking", "bonus", "status", "contract_name"))
                .flatMap(velib -> {
                    Velib.Field fields = velib.getRecords().get(0).getFields();
                    Velib.Station station = new Velib.Station(fields);
                    return Observable.just(station);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        station -> {//TODO
                            Log.d("Custom", "On nextO1");
                            Log.d("Custom", station.getAddress());
                            Log.d("Custom", "jkl");
                            //Snackbar.make(velibTile,"Connexion OK",Snackbar.LENGTH_INDEFINITE).show();
                            //textView.setText(velib.getRecords().get(0).getDatasetid());
                            stationsData.add(station);
                            stationAdapter.notifyDataSetChanged();
                            if (frag != null)
                                frag.setMarkerAtPosition(station.getPosition().get(0), station.getPosition().get(1),"Velib");

                        },
                        throwable -> {
                            throwable.printStackTrace();
                            Snackbar.make(velibTile, "Il y a un problème ", Snackbar.LENGTH_INDEFINITE)
                                    //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                    .show();
                            if (throwable.getMessage().contains("400"))
                                Snackbar.make(velibTile, "Vous n'avez peut être pas de compte", Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                        });
                        */

        //Obtention de la référence du service
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        // default
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = locationManager.getLastKnownLocation(provider);

        updateVelib();

        if(location!=null)
            frag.setMarkerAtPosition(location.getLatitude(),location.getLongitude(),"My Loc");

        //Si le GPS est disponible, on s'y abonne
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            abonnementGPS();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //On appelle la méthode pour se désabonner
        desabonnementGPS();
    }

    /**
     * Méthode permettant de s'abonner à la localisation par GPS.
     */
    public void abonnementGPS() {
        //On s'abonne
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

    }

    /**
     * Méthode permettant de se désabonner de la localisation par GPS.
     */
    public void desabonnementGPS() {
        //Si le GPS est disponible, on s'y abonne
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(final Location location) {
        //On affiche dans un Toat la nouvelle Localisation
        final StringBuilder msg = new StringBuilder("lat : ");
        msg.append(location.getLatitude());
        msg.append( "; lng : ");
        msg.append(location.getLongitude());

        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
        Log.e("Test",location.toString());
        frag.setMarkerAtPosition(location.getLatitude(),location.getLongitude(),"My Loc");
        updateVelib();


    }

    @Override
    public void onProviderDisabled(final String provider) {
        //Si le GPS est désactivé on se désabonne
        if("gps".equals(provider)) {
            desabonnementGPS();
        }
    }

    @Override
    public void onProviderEnabled(final String provider) {
        //Si le GPS est activé on s'abonne
        if("gps".equals(provider)) {
            abonnementGPS();
        }
    }

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
        Log.e("Test",provider);
    }


    public void updateVelib(){
        if(location!=null) {
            String locationString = location.getLatitude() + "," + location.getLongitude() + ",500";

            ApiManager.apiManagerDist.getVelib(
                    "stations-velib-disponibilites-en-temps-reel",
                    Arrays.asList("banking", "bonus", "status", "contract_name")
                    , locationString)
                    .flatMap(velib -> {
                        return Observable.from(velib.getRecords());
                    })
                    .flatMap(record -> {
                        Velib.Field fields = record.getFields();
                        Velib.Station station = new Velib.Station(fields);
                        return Observable.just(station);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            station -> {//TODO
                                Log.d("Custom", "On nextO1");
                                Log.d("Custom", station.getAddress());
                                Log.d("Custom", "jkl");
                                //Snackbar.make(velibTile,"Connexion OK",Snackbar.LENGTH_INDEFINITE).show();
                                //textView.setText(velib.getRecords().get(0).getDatasetid());
                                stationsData.add(station);
                                if (frag != null)
                                    frag.setMarkerAtPosition(station.getPosition().get(0), station.getPosition().get(1), "Velib");

                            },
                            throwable -> {
                                throwable.printStackTrace();
                                Snackbar.make(velibTile, "Il y a un problème ", Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                                if (throwable.getMessage().contains("400"))
                                    Snackbar.make(velibTile, "Vous n'avez peut être pas de compte", Snackbar.LENGTH_INDEFINITE)
                                            //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                            .show();
                            }, () -> {
                                stationAdapter.notifyDataSetChanged();
                            });
        }
    }
}
