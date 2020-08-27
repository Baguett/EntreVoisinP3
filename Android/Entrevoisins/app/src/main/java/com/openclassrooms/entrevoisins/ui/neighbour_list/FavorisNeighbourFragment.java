package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FavorisNeighbourFragment extends Fragment {

    private RecyclerView favRecyclerView;
    private NeighbourApiService favApiService;
    private List<Neighbour> favNeighbour;

    public static FavorisNeighbourFragment newInstance() {
        FavorisNeighbourFragment fragment = new FavorisNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favApiService=DI.getNeighbourApiService();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        favRecyclerView = (RecyclerView) view;
        favRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        favRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private void initList() {
        favNeighbour = favApiService.getNeighbours();
        favRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(favNeighbour,getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        favApiService.deleteNeighbour(event.neighbour);
        initList();
    }
}
