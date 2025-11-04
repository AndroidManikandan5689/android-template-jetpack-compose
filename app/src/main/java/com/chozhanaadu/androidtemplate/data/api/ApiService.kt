package com.chozhanaadu.androidtemplate.data.api

import com.chozhanaadu.androidtemplate.data.model.ItemDto
import retrofit2.http.GET

interface ApiService {
    @GET("api/articles")
    suspend fun getItems(): List<ItemDto>
}