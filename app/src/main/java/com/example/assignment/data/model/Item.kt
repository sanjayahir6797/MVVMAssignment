package com.example.assignment.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "items")
class Item {

    @PrimaryKey(autoGenerate = true)
    private val idItem = 0



    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("media")
    @Expose
    @Embedded var media: Media? = null

    @SerializedName("date_taken")
    @Expose
    var dateTaken: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("published")
    @Expose
    var published: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("author_id")
    @Expose
    var authorId: String? = null

    @SerializedName("tags")
    @Expose
    var tags: String? = null
}