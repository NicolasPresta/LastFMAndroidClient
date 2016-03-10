package com.example.presta.concepto.rest;

import com.example.presta.concepto.BuildConfig;
import com.example.presta.concepto.rest.deserializer.TagArtistsDeserializer;
import com.example.presta.concepto.rest.deserializer.TopArtistsDeserializer;
import com.example.presta.concepto.rest.model.TagArtistsResponse;
import com.example.presta.concepto.rest.model.TopArtistsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;

/**
 * Created by Presta on 08/03/2016.
 */
public class LastFMAPIAdapter {

    private static ILastFMApiService API_SERVICE;

    public static ILastFMApiService getApiService(){
        if(API_SERVICE == null) {

         RestAdapter adapter = new RestAdapter.Builder()
                 .setEndpoint(ApiConstants.URL_BASE)
                 .setLogLevel(RestAdapter.LogLevel.BASIC)
                 .setConverter(buildLastFmApiGsonConverter())
                 .build();

            API_SERVICE = adapter.create(ILastFMApiService.class);
        }

        return API_SERVICE;
    }

    private static GsonConverter buildLastFmApiGsonConverter() {
        Gson gsonConf = new GsonBuilder()
                .registerTypeAdapter(TopArtistsResponse.class, new TopArtistsDeserializer())
                .registerTypeAdapter(TagArtistsResponse.class, new TagArtistsDeserializer())
                .create();

        return new GsonConverter(gsonConf);
    }

    public static Observable<TagArtistsResponse> getTagArtist() {
        return getApiService().getTagArtist(obtainApiKey());
    }

    public static Observable<TopArtistsResponse> getTopArtist(){
        return getApiService().getTopArtists(obtainApiKey());
    }

    private static String obtainApiKey (){
        return BuildConfig.LAST_FM_API_KEY;
    }

}
