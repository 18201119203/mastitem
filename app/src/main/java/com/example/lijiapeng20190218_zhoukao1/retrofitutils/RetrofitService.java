package com.example.lijiapeng20190218_zhoukao1.retrofitutils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<ResponseBody> getReg(@Url String url);
}
