package com.test.hotelops.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.test.hotelops.ui.presentation.authentication.Authentication
import com.test.hotelops.ui.presentation.authentication.Create
import com.test.hotelops.ui.presentation.authentication.CreateHotelUI
import com.test.hotelops.ui.presentation.booking.CreateBookingUI
import com.test.hotelops.ui.presentation.home.HomeUI
import com.test.hotelops.ui.presentation.previouscustomer.AllCustomerUI

@Composable
fun PageNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = login, builder = {
        composable<login> {
            Authentication(navController = navController)
        }
        composable<create> {
            Create(navController = navController)
        }

        composable<home> {
            val args = it.toRoute<home>()
            HomeUI(navController = navController, userId = args.userId)
        }
        composable<createhotel> {
            CreateHotelUI(navController = navController)
        }
        composable<createbooking> {
            val args = it.toRoute<createbooking>()
            CreateBookingUI(navController = navController, userId = args.userId)
        }

        composable<allcustomer> {
            val args = it.toRoute<allcustomer>()
            AllCustomerUI(navController=navController, userId = args.userId)
        }


    })

}