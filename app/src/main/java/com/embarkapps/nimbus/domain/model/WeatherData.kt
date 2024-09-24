package com.embarkapps.nimbus.domain.model

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureFahrenheit: Double,
)
