package edu.course.littlelemon.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.course.littlelemon.room.MenuItemRoom
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemNetwork(
    val id: Long,
    val title : String,
    val description: String,
    val price : String,
    val image: String,
    val category: String) {

    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        price.toDoubleOrNull(),
        description,
        image,
        category
    )
}