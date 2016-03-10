package com.example.presta.concepto.rest;

import com.example.presta.concepto.BuildConfig;

/**
 * Created by Presta on 08/03/2016.
 */
public class ApiConstants {

    //public static final String API_KEY = "67638f096179ff62cfd490ec38546679";
   // public static final String API_KEY = BuildConfig.LAST_FM_API_KEY;

    public static final String PATH_VERSION = "/2.0";

    public static final String URL_BASE = "http://ws.audioscrobbler.com" + PATH_VERSION;

    public static final String PARAM_METHOD = "method";
    public static final String PARAM_FORMAT = "format";
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_COUNTRY = "country";

    public static final String VALUE_TOP_ARTIST_METHOD = "chart.gettopartists";
    public static final String VALUE_TAG_ARTIST_METHOD = "geo.gettopartists";

    public static final String VALUE_JSON = "json";
    public static final String VALUE_ARGENTINA = "argentina";

    // http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=67638f096179ff62cfd490ec38546679&format=json
    public static final String URL_TOP_ARTIST = "/?" +
                        PARAM_METHOD + "=" + VALUE_TOP_ARTIST_METHOD + "&" +
                        PARAM_FORMAT + "=" + VALUE_JSON;

    // http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=argentina&api_key=67638f096179ff62cfd490ec38546679&format=json
    public static final String URL_TAG_ARTIST = "/?" +
            PARAM_METHOD + "=" + VALUE_TAG_ARTIST_METHOD + "&" +
            PARAM_COUNTRY + "=" + VALUE_ARGENTINA + "&" +
            PARAM_FORMAT + "=" + VALUE_JSON;



}
