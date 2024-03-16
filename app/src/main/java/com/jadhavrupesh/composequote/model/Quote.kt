package com.jadhavrupesh.composequote.model


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("authorSlug")
    val authorSlug: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("dateAdded")
    val dateAdded: String = "",
    @SerializedName("dateModified")
    val dateModified: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("length")
    val length: Int = 0,
    @SerializedName("tags")
    val tags: List<String> = listOf()
)