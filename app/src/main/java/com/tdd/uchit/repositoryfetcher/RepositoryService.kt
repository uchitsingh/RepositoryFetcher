package com.tdd.uchit.repositoryfetcher

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tdd.uchit.repositoryfetcher.model.Repository
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.github.com/"
private const val END_POINT = "users/uchitsingh/repos"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object RepoApi {
    val retrofitService: RepoApiService by lazy {
        retrofit.create(RepoApiService::class.java)
    }
}

interface RepoApiService {
    @GET(END_POINT)
    fun getRepos(): Observable<List<Repository>>

}