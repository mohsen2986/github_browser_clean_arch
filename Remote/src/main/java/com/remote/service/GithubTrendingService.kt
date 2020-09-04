package com.remote.service

import com.remote.model.ProjectsResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService{

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String ,
        @Query("sort") sort: String ,
        @Query("order") order: String
    ) : Flowable<ProjectsResponseModel>
}