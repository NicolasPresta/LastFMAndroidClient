package com.example.presta.concepto.rest.deserializer;

import com.example.presta.concepto.domain.Artista;
import com.example.presta.concepto.rest.model.TagArtistsResponse;
import com.example.presta.concepto.rest.model.JsonKeys;
import com.example.presta.concepto.rest.model.TopArtistsResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Presta on 09/03/2016.
 */
public class TagArtistsDeserializer  implements JsonDeserializer<TagArtistsResponse> {

    @Override
    public TagArtistsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        TagArtistsResponse response = gson.fromJson(json, TagArtistsResponse.class);

        //The artists array will be parsed manually due nested elements
        JsonObject artistsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTIST_TOP_RESULTS);

        JsonArray artistsArray = artistsResponseData.getAsJsonArray(JsonKeys.ARTISTS_ARRAY);
        response.setArtists(extractArtistsFromJsonArray(artistsArray));

        return response;
    }

    private ArrayList<Artista> extractArtistsFromJsonArray(JsonArray artistsArray) {
        ArrayList<Artista> artists = new ArrayList<>();

        for (int i = 0; i < artistsArray.size(); i++) {
            JsonObject artistData = artistsArray.get(i).getAsJsonObject();

            Artista currentArtist = Artista.buildArtistFromJson(artistData);
            currentArtist.extractUrlsFromImagesArray(artistData.getAsJsonArray(JsonKeys.ARTIST_IMAGES));

            artists.add(currentArtist);

        }

        return artists;
    }
}
