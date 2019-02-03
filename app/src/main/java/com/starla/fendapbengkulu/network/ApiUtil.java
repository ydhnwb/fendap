package com.starla.fendapbengkulu.network;

import com.starla.fendapbengkulu.services.UserService;
import com.starla.fendapbengkulu.services.TourismSpotService;

public class ApiUtil {
    private ApiUtil(){ }

    public static final String API_URL = "http://fendap.herokuapp.com/";

    public static UserService getUserService(){ return RetrofitClient.Companion.getClient(API_URL).create(UserService.class); }

    public static TourismSpotService getWisataService(){
        return RetrofitClient.Companion.getClient(API_URL).create(TourismSpotService.class);
    }
//    public static PostService getPostService(){
//        return RetrofitClient.getClient(API_URL).create(PostService.class);
//    }
}
