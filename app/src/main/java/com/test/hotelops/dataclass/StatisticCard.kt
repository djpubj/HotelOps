package com.test.hotelops.dataclass

import androidx.compose.ui.graphics.Color

data class StatisticCard(
    val title: String,
    val value: String,
    val change: String,
    val percentage: String,
    val color: Color
)
