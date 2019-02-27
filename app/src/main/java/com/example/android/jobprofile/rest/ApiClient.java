package com.example.android.jobprofile.rest;

import android.app.Application;
import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Milind Amrutkar on 26-02-2019.
 */
public class ApiClient {

    private static String TAG = ApiClient.class.getSimpleName();

    private static final String AUTH = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdiYmZlNTI4YjI5OWVhOGZjNTg4OWM3N2M5NWZjMDliOGVmNmI0NGNiN2QyMTg4NzIxMWIxYjFjMTRjOTdhYjg4NGViNjQ2MTlhZWRjY2RiIn0.eyJhdWQiOiIzIiwianRpIjoiN2JiZmU1MjhiMjk5ZWE4ZmM1ODg5Yzc3Yzk1ZmMwOWI4ZWY2YjQ0Y2I3ZDIxODg3MjExYjFiMWMxNGM5N2FiODg0ZWI2NDYxOWFlZGNjZGIiLCJpYXQiOjE1NTEwMTEwMDcsIm5iZiI6MTU1MTAxMTAwNywiZXhwIjoxNTgyNTQ3MDA3LCJzdWIiOiIxOTMiLCJzY29wZXMiOltdfQ.enC6mmwbQPNgociOwaJbuWeGIL0v5t_54_cFyq-4UF-GkL6qiPHN0iTzwoVn3dT8SYO3zQoHF9ZiDNZt1HsdMowPp0qDS48OYC1yOk4jjMJpm6bG3a14dBT5C_lbutJxR1Hy64KZSM0AZDBQVI6wSBVsUX9PB5NO96bkaOfoojIFln5hJoszTe38ipurz0aFKfV-EN1lRT4WH603q7vAuepPWDy9-XrTEh13MrYBte6ioP0Jfspeoe35Kfv-0S0965tBTCzOzSHjEnPDAP8f3pjgqcrVk_RnEzibcI50A-DiZxjtuRJhzS8_KFfSoBiicbBaW-aTsU0VX2RM2ZLZ4sfgCsW_hm_8DLIfZBDySjS29uwjiRL1Z8gXYgr4fKw9xqGAKvTPcAqeDpDxgyeaS5tJI0R-x_PLl40dIWZPomymtF75QEOv_OwgLLvuFdY7Hu7lfe8q3RKYcr5g4dt4HYlJq1h4F1UyRowAcZXLTk5lCHzv1-KzKbeZ5ZfpkxzyIOQsP5xVDZDo0RMQ2aC7Jt000k8jkpH5DtkAR40G_mh7ffcNgpIQAFYIxXC7dAn6FVPiIv1o-xweRQa1P1j9AffpEABo6eYrrGFIihok5qOkLm4dSwUAnxB-Kfa0doz-7JJmzDTRH6zCsyvYl2oysHuXTesn1rieFFDThDErq3Q";

    public static final String BASE_URL = "https://apistage.thewift.com/1.0/";

    private static ApiClient mInstance;

    private Retrofit retrofit = null;

    //New lines added from here

    private ApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized ApiClient getInstance() {
        if(mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }

  /*  //Old way
    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Log.d(TAG, "retrofit: " + retrofit);

        return retrofit;
    }*/

}
