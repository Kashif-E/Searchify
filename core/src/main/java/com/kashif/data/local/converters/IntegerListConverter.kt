package com.kashif.data.local.converters


import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class IntegerListConverter {
    @TypeConverter
    fun fromString(value: String): List<Int>? {
        return Json.decodeFromString<List<Int>>(value)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        val json = Json
        return json.encodeToString(list)
    }
}
