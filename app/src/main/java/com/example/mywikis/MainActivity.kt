package com.example.mywikis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticleListAdapter
    private lateinit var service: WikipediaApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(WikipediaApi::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = ArticleListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        search("android")
    }

    private fun search(term: String) {
        service.search(
            "query",
            "json",
            "search",
            term,
            "1",
            1,
            "origin",
        ).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                if (response.isSuccessful && response.body() != null) {

                    val articles = response.body()?.query?.search
                    if (articles != null) {
                        adapter.setArticles(articles)
                    } else {
                        // handle case where response body or query is null
                    }
                } else {
                    // handle error case
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                // handle failure
            }
        })
    }
}