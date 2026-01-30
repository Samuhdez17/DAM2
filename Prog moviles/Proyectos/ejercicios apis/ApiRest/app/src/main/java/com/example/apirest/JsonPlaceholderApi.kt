package com.example.apirest

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(@Query("posts") nombre: String): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post
}