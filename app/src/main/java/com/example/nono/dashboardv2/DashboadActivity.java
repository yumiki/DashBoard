package com.example.nono.dashboardv2;

import android.database.DataSetObserver;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nono.dashboardv2.data.Tile;
import com.example.nono.dashboardv2.util.ApiManager;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);
        View velibTile = findViewById(R.id.velibTile);
        TextView textView= (TextView) velibTile.findViewById(R.id.velibTitle);

        ApiManager.apiManagerDist.getVelib(
                "stations-velib-disponibilites-en-temps-reel",
                "20 Rue Guillaume Bertrand",
                Arrays.asList("banking","bonus","status","contract_name"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        velib -> {//TODO
                            Log.d("Custom","On nextO1");
                            Log.d("Custom",velib.getRecords().get(0).getDatasetid());
                            Log.d("Custom","jkl");
                            Toast.makeText(this,"FFF", Toast.LENGTH_SHORT).show();
                            Snackbar.make(velibTile,"Test",Snackbar.LENGTH_LONG).show();
                            //Snackbar.make(mOutput,"Next:"+authToken.getValue(),Snackbar.LENGTH_LONG).show();

                            Snackbar.make(velibTile,"Connexion OK",Snackbar.LENGTH_INDEFINITE)
                                    //.setAction("Homepage",view -> toNextTheActivity(login,password,HomepageActivity.class))
                                    .show();
                            //toNextTheActivity(User.EMAIL,User.PASSWORD,HomepageActivity.class);
                            textView.setText(velib.getRecords().get(0).getDatasetid());

                        },
                        throwable -> {
                            throwable.printStackTrace();
                            if(throwable.getMessage().contains("200"))
                                Snackbar.make(velibTile,"A mon avis ",Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                            if(throwable.getMessage().contains("400"))
                                Snackbar.make(velibTile,"Vous n'avez peut être pas de compte",Snackbar.LENGTH_INDEFINITE)
                                        //.setAction("Register",view -> toNextTheActivity(login,password,SignupActivity.class))
                                        .show();
                        });

    }
}
