package com.repa007.calc_v2

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update


@Entity
class DBHistory {
    @PrimaryKey
    var id: Long = 0
    var calculation: String? = null
    var result: String? = null
    var time: String? = null
}

@Dao
interface DBHistoryDAO {
    @get:Query("SELECT * FROM dbhistory")
    val all: List<DBHistory?>?

    @Query("SELECT * FROM dbhistory WHERE id = :id")
    fun getById(id: Long): DBHistory?

    @Insert
    fun insert(entry: DBHistory?)

    @Update
    fun update(entry: DBHistory?)

    @Delete
    fun delete(entry: DBHistory?)
}

@Database(entities = [DBHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): DBHistoryDAO?
}