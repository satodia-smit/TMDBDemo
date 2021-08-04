package com.hyperelement.mvvmdemo.data.datasources.local

import com.hyperelement.mvvmdemo.BuildConfig
import com.hyperelement.mvvmdemo.data.entity.search.SearchBaseRes
import retrofit2.http.*

interface SearchAPIService {

    @GET("search/movie/?api_key=${BuildConfig.API_KEY}")
    suspend fun getSearchedItem(
        @Query("query") query: String,
        @Query("page") aPage: Int
    ): SearchBaseRes

}