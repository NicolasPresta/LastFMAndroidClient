package com.example.presta.concepto.rest.model;

import com.example.presta.concepto.domain.Artista;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Presta on 09/03/2016.
 */
public class TagArtistsResponse {

    @SerializedName(JsonKeys.ARTIST_TOP_RESULTS)
    private MainResponse mainResponse;

    public ArrayList<Artista> getArtists(){
        return  mainResponse.artists;
    }

    public void setArtists(ArrayList<Artista> artists) {
        mainResponse.artists = artists;
    }

    private class MainResponse {
        private ArrayList<Artista> artists;

    }
}
