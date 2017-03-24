package com.example.nono.dashboardv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nono.dashboardv2.data.Tile;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nono on 24/03/2017.
 */

public class TilesAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<Tile> tiles;
    private Context context;

    public TilesAdapter(List<Tile> tiles, Context context) {
        this.tiles = tiles;
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder listViewHolder;
        if(view == null){
            listViewHolder = new ViewHolder();
            view = layoutinflater.inflate(R.layout.tiles_layout, viewGroup, false);
            //listViewHolder.imageView = (TextView)view.findViewById(R.id.textView);
            listViewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);
            view.setTag(listViewHolder);

            //view.getLayoutParams().
        }else{
            listViewHolder = (ViewHolder)view.getTag();
        }

        //listViewHolder.textInListView.setText(listStorage.get(position).getContent());
        //int imageResourceId = this.context.getResources().getIdentifier(tiles.get(position).getImageResource(), "drawable", this.context.getPackageName());
        //listViewHolder.imageInListView.setImageResource(imageResourceId);

        Picasso.with(context)
                .load("http://i.imgur.com/DvpvklR.png")
                .fit()
                .into(listViewHolder.imageView);


        return view;
    }


    public class ViewHolder{
        ImageView imageView;

    }
}
