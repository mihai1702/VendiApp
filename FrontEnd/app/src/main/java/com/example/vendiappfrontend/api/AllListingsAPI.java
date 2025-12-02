package com.example.vendiappfrontend.api;


import com.example.vendiappfrontend.Listing;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface AllListingsAPI {
    @GET("/listings") // Endpoint-ul trebuie să corespundă cu cel din backend!
    Call<List<Listing>> read_listing();
}
