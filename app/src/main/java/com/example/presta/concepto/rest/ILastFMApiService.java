package com.example.presta.concepto.rest;

import com.example.presta.concepto.rest.model.TagArtistsResponse;
import com.example.presta.concepto.rest.model.TopArtistsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by Presta on 08/03/2016.
 */
public interface ILastFMApiService {
    
    @GET(ApiConstants.URL_TOP_ARTIST)
    void getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key,Callback<TopArtistsResponse> serverResponse);

    @GET(ApiConstants.URL_TAG_ARTIST)
    void getTagArtist(@Query(ApiConstants.PARAM_API_KEY) String key,Callback<TagArtistsResponse> serverRespones);

    @GET(ApiConstants.URL_TOP_ARTIST)
    Observable<TopArtistsResponse> getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key);

    @GET(ApiConstants.URL_TAG_ARTIST)
    Observable<TagArtistsResponse> getTagArtist(@Query(ApiConstants.PARAM_API_KEY) String key);

}
