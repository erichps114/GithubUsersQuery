package com.project.githubusers.rest

import com.project.githubusers.model.ResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/users")
    fun searchUsers(@Query("q")query : String,@Query("page")currentPage:Int) : Call<ResponseModel>

    companion object {
        val instance: ApiInterface by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiInterface::class.java)
        }
    }
}