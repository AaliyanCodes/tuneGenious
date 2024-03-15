package com.example.aitonify

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MusicApi {
    @Multipart
    @POST("generate_audio")
    fun getMusicByPrompt(
        @Part("api_key") api_key: RequestBody,
        @Part("prompt") prompt: RequestBody,



        ): Call<ResponseBody>

}
