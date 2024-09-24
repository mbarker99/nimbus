package com.embarkapps.nimbus.data.repository

import com.embarkapps.nimbus.data.remote.WeatherApi
import com.embarkapps.nimbus.data.remote.model.WeatherDataDto
import com.embarkapps.nimbus.data.remote.model.WeatherDto
import com.embarkapps.nimbus.domain.model.WeatherData
import com.embarkapps.nimbus.domain.model.WeatherInfo
import com.embarkapps.nimbus.domain.repository.WeatherRepository
import com.embarkapps.nimbus.domain.util.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Response<WeatherInfo> {
        return try {
            Response.Success(
                data = apiService.getWeather(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: " An unexpected error has occurred.")
        }
    }

    fun WeatherDto.toWeatherInfo(): WeatherInfo {
        val weatherDataMap: Map<Int, List<WeatherData>> = weatherData.toWeatherDataMap()
        val now = LocalDateTime.now()
        val currentWeatherData = weatherDataMap[0]?.find {
            val hour = if (now.minute < 30) now.hour else now.hour + 1
            it.time.hour == hour
        }
        return WeatherInfo(
            dailyWeatherData = weatherDataMap,
            currentWeatherData = currentWeatherData
        )
    }

    fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
        return time.mapIndexed { index, time ->
            val temperature = temperatures[index]

            IndexedWeatherData(
                index = index,
                data = WeatherData(
                    time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                    temperatureFahrenheit = temperature
                )
            )
        }.groupBy {
            it.index / 24
        }.mapValues {
            it.value.map { it.data }
        }
    }

    private data class IndexedWeatherData(
        val index: Int,
        val data: WeatherData
    )
}