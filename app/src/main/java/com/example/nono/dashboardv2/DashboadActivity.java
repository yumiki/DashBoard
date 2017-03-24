package com.example.nono.dashboardv2;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.nono.dashboardv2.data.Tile;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboadActivity extends AppCompatActivity {

    GridView gridView;
    AsymmetricGridView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);
        List<Tile> tiles = new ArrayList<>();

        for(int i=0;i<10;i++)
            tiles.add(new Tile());

        gridView = (GridView) findViewById(R.id.grid_view);
        listView = (AsymmetricGridView) findViewById(R.id.listView);

        listView.setRequestedColumnWidth(Utils.dpToPx(this, 120));


        gridView.setAdapter(new TilesAdapter(tiles,getBaseContext()));
        //gridView.setLiff
        listView.setAllowReordering(true);

        listView.isAllowReordering(); // true
    }
}
