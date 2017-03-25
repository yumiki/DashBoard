package com.example.nono.dashboardv2;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nono.dashboardv2.data.Velib;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by nono on 24/03/2017.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    List<Velib.Station> stations;
    Context mContext;

    public StationAdapter(List<Velib.Station> stations, Context mContext) {
        this.stations = stations;
        this.mContext = mContext;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Vue de la cellule
        View view = inflater.inflate(R.layout.velib_station_details, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        Velib.Station station = stations.get(position);

        holder.display(station,position);
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public class StationViewHolder extends RecyclerView.ViewHolder {

        private final TextView stationName;
        private final TextView stationAddresse;
        private final TextView stationBikeAvailable;

        private Velib.Station currentStation;

        public StationViewHolder(final View itemView) {
            super(itemView);

            stationName = (TextView) itemView.findViewById(R.id.station_name_tv);
            stationAddresse = (TextView) itemView.findViewById(R.id.station_adresse_tv);
            stationBikeAvailable = (TextView) itemView.findViewById(R.id.number_of_bike_stand_available);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Station :"+currentStation.getName())
                            .setMessage("quantité:"+currentStation.getAvailableBikes())
                            .show();
                }
            });
        }


        public void display(Velib.Station station, int position) {
            currentStation = station;
            stationName.setText(currentStation.getName());
            stationAddresse.setText(currentStation.getAddress());

            stationBikeAvailable.setText(currentStation.getAvailableBikeStands()+"");
        }

    }

}
