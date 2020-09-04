package com.example.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CachedProjectsDao
import com.example.cache.dao.ConfigDao

@Database(entities = [
    CachedProjectsDao::class ,
    ConfigDao::class
    ]
    , version = 1)
abstract class ProjectsDatabase: RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao
    abstract fun configDao(): ConfigDao

    private var INSTANCE: ProjectsDatabase? = null
    private val lock = Any()

    fun getInstance(context: Context): ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectsDatabase::class.java,
                        "projects.db"
                    ).build()
                }
            }
        }
        return INSTANCE as ProjectsDatabase
    }

}
