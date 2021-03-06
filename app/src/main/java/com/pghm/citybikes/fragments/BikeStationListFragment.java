package com.pghm.citybikes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pghm.citybikes.Elements.BikeStationFragmentHost;
import com.pghm.citybikes.Elements.BikeStationListAdapter;
import com.pghm.citybikes.R;
import com.pghm.citybikes.Util;
import com.pghm.citybikes.models.BikeStation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BikeStationListFragment extends Fragment {

    @BindView(R.id.list_view) ListView list;
    @BindView(R.id.empty_view) TextView emptyView;

    private BikeStationFragmentHost host;
    private BikeStationListAdapter adapter;
    private List<BikeStation> listData = new ArrayList<>();

    /* Required empty initializer */
    public BikeStationListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new BikeStationListAdapter(
                getActivity(), R.layout.element_bike_station, listData);
        list.setEmptyView(emptyView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                host.centerMapOnStation(listData.get(i).getId());
            }
        });

        host.fragmentLoaded();
        return view;
    }

    public void updateStations(final Collection<BikeStation> stations) {
        listData.clear();
        for (BikeStation station : stations) {
            listData.add(station);
        }
        Util.sortStationsByName(listData);
        adapter.notifyDataSetChanged();
    }

        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            host = (BikeStationFragmentHost)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BikeStationFragmentHost");
        }
    }
}
