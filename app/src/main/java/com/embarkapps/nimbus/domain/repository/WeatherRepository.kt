package com.embarkapps.nimbus.domain.repository

import com.embarkapps.nimbus.domain.model.WeatherInfo
import com.embarkapps.nimbus.domain.util.Response
import java.util.concurrent.Flow

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double) : Response<WeatherInfo>
}