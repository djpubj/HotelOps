package com.test.hotelops.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StatisticCar(
    title: String,
    yesterdayValue: String,
    todayValue: String,
    percentageChange: String,
    percentageColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Y'day: $yesterdayValue", fontSize = 12.sp, color = Color.Gray)
            Text(
                text = todayValue,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Blue
            )
            Text(text = percentageChange, fontSize = 14.sp, color = percentageColor)
        }
    }
}
