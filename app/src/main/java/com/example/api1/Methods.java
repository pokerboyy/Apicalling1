package com.example.api1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("b/63OH")
    Call<Model>getAllData();
}
