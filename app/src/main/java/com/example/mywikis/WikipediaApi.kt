package com.example.mywikis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WikipediaApi {
    @GET("w/api.php")
    fun search(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String,
        @Query("utf8") utf8: String,
        @Query("formatversion") formatversion: Int,
        @Query("origin") origin: String,
    ): Call<ArticleResponse>
}
