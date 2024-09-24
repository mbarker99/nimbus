package com.embarkapps.nimbus.domain.model

data class WeatherInfo (
    val dailyWeatherData: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)