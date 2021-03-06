package com.pghm.citybikes.Elements;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pghm.citybikes.R;
import com.pghm.citybikes.Util;
import com.pghm.citybikes.models.BikeStation;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Jussi on 3.7.2016.
 */
public class BikeStationListAdapter extends ArrayAdapter<BikeStation> {

    public BikeStationListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public BikeStationListAdapter(Context context, int resource, List<BikeStation> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.element_bike_station, null);
        }

        final BikeStation station = getItem(position);

        if (station != null) {
            ImageView status = ButterKnife.findById(v, R.id.status);
            TextView name = ButterKnife.findById(v, R.id.name);
            TextView freeBikes = ButterKnife.findById(v, R.id.free_bikes);

            name.setText(station.getName());
            freeBikes.setText(station.getFreeBikesText(getContext()));
            status.setImageDrawable(ContextCompat.getDrawable(getContext(),
                    Util.getBikeIconResource(station.getBikesAvailable())));
        }

        return v;
    }

}
