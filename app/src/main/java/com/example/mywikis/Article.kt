package com.example.mywikis

import retrofit2.http.Query

data class Article (
    val title: String,
    val description: String,
    val image: String
)
