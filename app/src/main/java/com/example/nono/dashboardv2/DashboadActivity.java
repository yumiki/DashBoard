package com.example.nono.dashboardv2;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.nono.dashboardv2.data.Velib;
import com.example.nono.dashboardv2.util.ApiManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboadActivity extends AppCompatActivity implements MapTileFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);
        View velibTile = findViewById(R.id.velibTile);
        RecyclerView velibStationView = (RecyclerView) velibTile.findViewById(R.id.velibRv);

        List<Velib.Station> stationsData=new ArrayList<>();

        velibStationView.setLayoutManager(new LinearLayoutManager(this));

        StationAdapter stationAdapter = new StationAdapter(stationsData,getBaseContext());
        velibStationView.setAdapter(stationAdapter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mapTile,new MapTileFragment(),"MapTile")
                .addToBackStack("MapTile")
                .commit();
        //MapTileFragment frag = (MapTileFragment) getSupportFragmentManager().findFragmentByTag("MapTile");

        //if(frag == null)
         //   Snackbar.make(velibTile,"Il y a un problème 1",Snackbar.LENGTH_INDEFINITE).show();


        ApiManager.apiManagerDist.getVelib(
                "stations-velib-disponibilites-en-temps-reel",
                "20 Rue Guillaume Bertrand",
                Arrays.asList("banking","bonus","status","contract_name"))
                .flatMap(velib -> {
                    Velib.Field fields = velib.getRecords().get(0).getFields();
                    Velib.Station station =new Velib.Station(fields);
                    return Observable.just(station);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        station -> {//TODO
                            Log.d("Custom","On nextO1");
                            Log.d("Custom",station.getAddress());
                            Log.d("Custom","jkl");
                            //Snackbar.make(velibTile,"Connexion OK",Snackbar.LENGTH_INDEFINITE).show();
                            //textView.setText(velib.getRecords().get(0).getDatasetid());
                            stationsData.add(station);
                            stationAdapter.notifyDataSetChanged();
                            MapTileFragment frag = (MapTileFragment) getSupportFragmentManager().findFragmentByTag("MapTile");
                            if(frag!=null)
                                frag.setMarkerAtPosition(station.getPosition().get(0),station.getPosition().get(1));

                        },
                        throwable -> {
                            throwable.printStackTrace();
                                Snackbar.make(velibTile,"Il y a un problème ",Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                            if(throwable.getMessage().contains("400"))
                                Snackbar.make(velibTile,"Vous n'avez peut être pas de compte",Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
