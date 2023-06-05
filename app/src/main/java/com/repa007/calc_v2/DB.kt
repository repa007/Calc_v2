package com.repa007.calc_v2

import android.app.Application
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.Update



@Entity
class DBHistory {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var calculation: String? = null
    var result: String? = null
    var time: String? = null
}

@Dao
interface DBHistoryDao {
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
    abstract fun dbHistoryDAO(): DBHistoryDao?
}

class App : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        var instance: App? = null
    }
}