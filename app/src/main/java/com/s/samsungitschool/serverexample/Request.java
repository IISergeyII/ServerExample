package com.s.samsungitschool.serverexample;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Request {
    @FormUrlEncoded
    @POST("user/edit")
    Call<Object> performPostCall(@FieldMap HashMap<String, String> postDataParams);
}
