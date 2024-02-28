package com.example.assignment.data.cache

import androidx.room.TypeConverter
import com.example.assignment.data.model.Media
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMedia(media: Media): String {
        return gson.toJson(media)
    }

    @TypeConverter
    fun toMedia(json: String): Media {
        return gson.fromJson(json, Media::class.java)
    }
}