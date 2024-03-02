package com.example.assignment.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "items")
class Item {

    /*@PrimaryKey(autoGenerate = true)
    private val idItem = 0*/
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long=0

    //@ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = null

    //@ColumnInfo(name = "link")
    @SerializedName("link")
    @Expose
    var link: String? = null

   // @ColumnInfo(name = "media")
    @SerializedName("media")
    @Expose
    @Embedded var media: Media? = null

    //@ColumnInfo(name = "date_taken")
    @SerializedName("date_taken")
    @Expose
    var dateTaken: String? = null

    //@ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    var description: String? = null

    //@ColumnInfo(name = "published")
    @SerializedName("published")
    @Expose
    var published: String? = null

    //@ColumnInfo(name = "author")
    @SerializedName("author")
    @Expose
    var author: String? = null

    //@ColumnInfo(name = "author_id")
    @SerializedName("author_id")
    @Expose
    var authorId: String? = null

    //@ColumnInfo(name = "tags")
    @SerializedName("tags")
    @Expose
    var tags: String? = null
}