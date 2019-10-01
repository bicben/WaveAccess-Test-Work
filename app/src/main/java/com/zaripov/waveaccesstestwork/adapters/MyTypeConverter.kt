package com.zaripov.waveaccesstestwork.adapters

import android.arch.persistence.room.TypeConverter
import com.zaripov.waveaccesstestwork.model.EyeColor
import com.zaripov.waveaccesstestwork.model.Fruit
import java.util.*

class MyTypeConverter {
    @TypeConverter
    fun eyeColorFromEnumToString(value: EyeColor?): String? {
        return value?.name?.toLowerCase(Locale.ENGLISH)
    }

    @TypeConverter
    fun eyeColorFromStringToEnum(value: String?): EyeColor? {
        return value?.let { EyeColor.valueOf(it.toUpperCase(Locale.ENGLISH)) }
    }

    @TypeConverter
    fun fruitFromEnumToString(value: Fruit?): String? {
        return value?.name?.toLowerCase(Locale.ENGLISH)
    }

    @TypeConverter
    fun fruitFromStringToEnum(value: String?): Fruit? {
        return value?.let { Fruit.valueOf(it.toUpperCase(Locale.ENGLISH)) }
    }
}