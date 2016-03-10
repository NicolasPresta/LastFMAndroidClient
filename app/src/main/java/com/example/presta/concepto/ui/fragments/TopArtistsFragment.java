package com.example.presta.concepto.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.concepto.R;
import com.example.presta.concepto.rest.LastFMAPIAdapter;
import com.example.presta.concepto.rest.model.TagArtistsResponse;
import com.example.presta.concepto.rest.model.TopArtistsResponse;
import com.example.presta.concepto.ui.decorators.ItemOffsetDecoration;
import com.example.presta.concepto.ui.adapter.TopArtistAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class TopArtistsFragment extends Fragment {

    //region "-- CONSTANTS --"

    public static final int NUM_COLUMNAS = 2;

    //endregion

    //region "-- ATRIBUTES --"

    private RecyclerView mTopArtistsList;
    private TopArtistAdapter adapter;

    //endregion

    //region "-- CONSTRUCTOR --"

    public TopArtistsFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TopArtistAdapter(getActivity());
        //setDummyContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Infla el fragmento dentro de la activity
        View root = inflater.inflate(R.layout.fragment_top_artists, container, false);

        // Instancia un RecyclerView
        mTopArtistsList = (RecyclerView) root.findViewById(R.id.top_artists_list);

        // Hace el setup del RecyclerView
        setupArtistsList();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestTopArtists();
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void setupArtistsList() {
        // Instancia el manager del RecyclerView, usamos un GridLayoutManager
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mTopArtistsList.setLayoutManager(gridLayoutManager);
        mTopArtistsList.setAdapter(adapter);
        mTopArtistsList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
    }

    private void requestTopArtists() {
        LastFMAPIAdapter.getTopArtist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopArtistsResponse>() {
                    @Override
                    public void onNext(TopArtistsResponse topArtistsResponse) {
                        adapter.addAll(topArtistsResponse.getArtists());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    //endregion

}