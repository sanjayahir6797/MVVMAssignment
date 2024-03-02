package com.example.assignment.data.remote

import com.example.assignment.data.model.JsonResponse
import retrofit2.http.GET

interface ApiService {


    @GET("services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun fetchData(): JsonResponse // Define your data model


}
