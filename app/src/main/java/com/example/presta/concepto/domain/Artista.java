package com.example.presta.concepto.domain;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.presta.concepto.rest.model.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 07/03/2016.
 */
public class Artista {

    //region "-- CONSTRUCTORS --"

    public Artista(String name) {
        this.nombre = name;
    }

    // Static class constructor from Json
    public static Artista buildArtistFromJson(JsonObject artistData) {
        Gson gson = new Gson();

        return gson.fromJson(artistData, Artista.class);
    }

    //endregion

    //region "-- ATRIBUTES --"

    @SerializedName(JsonKeys.ARTIST_NAME)
    private String nombre;

    @SerializedName(JsonKeys.ARTIST_LISTENERS)
    private String listeners;

    @SerializedName(JsonKeys.ARTIST_PLAY_COUNT)
    private String playCount;

    @Nullable
    private String urlMediumImage;

    @Nullable
    private String urlLargeImage;

    //endregion

    //region "-- SETTERS --"

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public void setUrlMediumImage(@Nullable String urlMediumImage) {
        this.urlMediumImage = urlMediumImage;
    }

    public void setUrlLargeImage(@Nullable String urlLargeImage) {
        this.urlLargeImage = urlLargeImage;
    }

    //endregion

    //region "-- GETTERS --"

    public String getListeners() {
        return listeners;
    }

    public String getPlayCount() {
        return playCount;
    }

    public String getNombre() {
        return nombre;
    }

    @Nullable
    public String getUrlMediumImage() {
        return urlMediumImage;
    }

    @Nullable
    public String getUrlLargeImage() {
        return urlLargeImage;
    }

    //endregion

    //region "-- PUBLIC METHODS --"

    public void extractUrlsFromImagesArray(JsonArray imagesJson) {
        String[] images = new String[2];

        for (int i = 0; i < imagesJson.size(); i++) {
            JsonObject currentImage = imagesJson.get(i).getAsJsonObject();

            String size = currentImage.get(JsonKeys.IMAGE_SIZE).getAsString();
            String url = currentImage.get(JsonKeys.IMAGE_URL).getAsString();

            url = url.replaceAll("\\/", "/");

            if (url.isEmpty())
                url = null;

            if (size.matches(JsonKeys.IMAGE_MEDIUM))
                images[0] = url;

            else if (size.matches(JsonKeys.IMAGE_LARGE))
                images[1] = url;

        }

        //Set the images
        setUrlMediumImage(images[0]);
        setUrlLargeImage(images[1]);
    }

    //endregion
}
