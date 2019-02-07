package com.starla.fendapbengkulu.network;

import com.starla.fendapbengkulu.services.TourismSpotService;

public class ApiUtil {
    private ApiUtil(){ }

    public static final String API_URL = "https://fendap.bengkulu.i-applicat.com/";

    public static TourismSpotService getTourismSpotService(){
        return RetrofitClient.Companion.getClient(API_URL).create(TourismSpotService.class);
    }
}
