package com.embarkapps.nimbus.data.remote.model

import com.embarkapps.nimbus.domain.model.WeatherData
import com.embarkapps.nimbus.domain.model.WeatherInfo
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import com.embarkapps.nimbus.data.remote.model.WeatherDataDto

data class WeatherDto(
    @SerializedName("hourly")
    val weatherData: WeatherDataDto
)