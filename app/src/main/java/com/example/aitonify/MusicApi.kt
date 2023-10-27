package com.example.aitonify

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface MusicApi {
    @Multipart
    @POST("generate-audio")
    suspend fun getMusicByPrompt(
        @Part prompt: MultipartBody.Part,
        @Part key: MultipartBody.Part,
        @Part negative_prompt: MultipartBody.Part,
        @Part guidance_scale: MultipartBody.Part,
        @Part audio_length_in_s: MultipartBody.Part,
        @Part seed: MultipartBody.Part
    ): Call<ResponseBody>

}
