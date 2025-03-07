package edu.course.littlelemon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData( @SerialName("menu") val menu : List<MenuItemNetwork> = listOf())
