package com.example.android.jobprofile.rest;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Milind Amrutkar on 26-02-2019.
 */
public class ApiClient {

    private static String TAG = ApiClient.class.getSimpleName();

    public static final String BASE_URL = "https://apistage.thewift.com/1.0/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Log.d(TAG, "retrofit: " + retrofit);

        return retrofit;
    }
}
