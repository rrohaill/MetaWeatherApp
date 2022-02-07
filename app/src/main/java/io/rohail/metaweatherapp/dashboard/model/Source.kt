package io.rohail.metaweatherapp.dashboard.model


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("title") val title: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("url") val url: String,
    @SerializedName("crawl_rate") val crawlRate: Int
)