package com.example.presta.concepto.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.concepto.R;
import com.example.presta.concepto.rest.LastFMAPIAdapter;
import com.example.presta.concepto.rest.model.TagArtistsResponse;
import com.example.presta.concepto.ui.adapter.TagArtistAdapter;
import com.example.presta.concepto.ui.decorators.ItemOffsetDecoration;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 */

public class TagArtistFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mTagArtistsList;
    private TagArtistAdapter adapter;

    //endregion

    //region "-- CONSTRUCTOR --"

    public TagArtistFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TagArtistAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tag_artist, container, false);

        mTagArtistsList = (RecyclerView) root.findViewById(R.id.tag_artists_list);

        // Hace el setup del RecyclerView
        setupArtistsList();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestTagArtists();
       /* LastFMAPIAdapter.getApiService()
                .getTagArtist(this);*/
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void setupArtistsList(){
        mTagArtistsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTagArtistsList.setAdapter(adapter);
        mTagArtistsList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
    }

    private void requestTagArtists() {
        LastFMAPIAdapter.getTagArtist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Subscriber<TagArtistsResponse>(){
                    @Override
                    public void onNext(TagArtistsResponse tagArtistsResponse) {
                        adapter.addAll(tagArtistsResponse.getArtists());
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
