package com.example.lijiapeng20190218_zhoukao1.retrofitutils;

import com.example.lijiapeng20190218_zhoukao1.api.Api;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private RetrofitUtils() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient ok = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(ok)
                .build();
    }

    public static RetrofitUtils initRetrofitUtils(){

        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public void doGet(String url, final RetrofitCallback callback){

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getReg(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    if (callback!=null){
                        try {
                            String string = response.body().string();
                            callback.Success(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                if (callback!=null){
                    callback.Failure("网络不稳定,请稍后重试");
                }

            }
        });


    }



}
