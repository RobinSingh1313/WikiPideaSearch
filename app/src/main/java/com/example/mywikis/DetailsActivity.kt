package com.example.mywikis
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var titleView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        titleView = findViewById(R.id.title)
        imageView = findViewById(R.id.image)

        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("image")

        titleView.text = title
        Glide.with(this).load(imageUrl).into(imageView)
    }
}
