package edu.course.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemonPrefs", Context.MODE_PRIVATE)
    val firstName : String? = sharedPreferences.getString("firstName", "")
    val lastName : String? = sharedPreferences.getString("lastName", "")
    val email : String? = sharedPreferences.getString("email", "")

    val logged = firstName?.isNotBlank()?: false && lastName?.isNotBlank()?: false && email?.isNotBlank()?: false

    NavHost( navController = navController,
        startDestination =  if (logged) HomeDestination else OnboardingDestination
    ) {
        composable<HomeDestination>{ Home(navController) }
        composable<OnboardingDestination> { Onboarding(navController) }
        composable<ProfileDestination> { Profile(navController) }
    }
}