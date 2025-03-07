package edu.course.littlelemon.navigation
import kotlinx.serialization.Serializable


interface Destinations {
    val nome : String
}

@Serializable
object HomeDestination : Destinations { override val nome = "Home" }

@Serializable
object OnboardingDestination : Destinations { override val nome = "Onboarding" }

@Serializable
object ProfileDestination : Destinations { override val nome = "Profile" }
