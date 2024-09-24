package com.embarkapps.nimbus.data.remote.model

import com.embarkapps.nimbus.domain.model.WeatherData
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherDataDto(
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    val time: List<String>
)