package edu.course.littlelemon.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Long,
    val title: String,
    val price: Double?,
    val description: String,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemRoom")
    suspend fun getAll() : List<MenuItemRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( items : List<MenuItemRoom> )

    @Delete
    suspend fun delete( item : MenuItemRoom )

    @Query("DELETE FROM MenuItemRoom")
    suspend fun deleteAll()
}

@Database(entities = [MenuItemRoom::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao() : MenuItemDao
}