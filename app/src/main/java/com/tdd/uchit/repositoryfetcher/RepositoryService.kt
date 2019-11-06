package com.tdd.uchit.repositoryfetcher

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tdd.uchit.repositoryfetcher.model.Repository
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.github.com/"
private const val END_POINT = "users/uchitsingh/repos"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object RepoApi {
    val retrofitService: RepoApiService by lazy {
        retrofit.create(RepoApiService::class.java)
    }
}

interface RepoApiService {
    @GET(END_POINT)
    fun getRepos(): Deferred<List<Repository>>

}