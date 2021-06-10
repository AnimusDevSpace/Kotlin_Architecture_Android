package com.assignment.cleanarchitecture.ui.data.model

import com.google.gson.annotations.SerializedName

@Serializable
data class MostPopularArticles(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
) {
    @Serializable
    data class Result(
        @SerializedName("abstract")
        val `abstract`: String,
        @SerializedName("adx_keywords")
        val adxKeywords: String,
        @SerializedName("asset_id")
        val assetId: Long,
        @SerializedName("byline")
        val byline: String,
        @SerializedName("column")
        val column: Any,
        @SerializedName("des_facet")
        val desFacet: List<String>,
        @SerializedName("eta_id")
        val etaId: Int,
        @SerializedName("geo_facet")
        val geoFacet: List<String>,
        @SerializedName("id")
        val id: Long,
        @SerializedName("media")
        val media: List<Media>,
        @SerializedName("nytdsection")
        val nytdsection: String,
        @SerializedName("org_facet")
        val orgFacet: List<String>,
        @SerializedName("per_facet")
        val perFacet: List<String>,
        @SerializedName("published_date")
        val publishedDate: String,
        @SerializedName("section")
        val section: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("subsection")
        val subsection: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated")
        val updated: String,
        @SerializedName("uri")
        val uri: String,
        @SerializedName("url")
        val url: String
    ) {
        @Serializable
        data class Media(
            @SerializedName("approved_for_syndication")
            val approvedForSyndication: Int,
            @SerializedName("caption")
            val caption: String,
            @SerializedName("copyright")
            val copyright: String,
            @SerializedName("media-metadata")
            val mediaMetadata: List<MediaMetadata>,
            @SerializedName("subtype")
            val subtype: String,
            @SerializedName("type")
            val type: String
        ) {
            @Serializable
            data class MediaMetadata(
                @SerializedName("format")
                val format: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("url")
                val url: String,
                @SerializedName("width")
                val width: Int
            )
        }
    }
}
