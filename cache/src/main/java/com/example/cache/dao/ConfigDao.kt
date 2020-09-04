package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cache.db.ConfigConstants.QUERY_CONFIG
import com.example.cache.model.Config
import io.reactivex.Single

@Dao
abstract class ConfigDao{

    @Insert(onConflict = REPLACE)
    abstract fun insertConfig(config: Config)

    @Query(QUERY_CONFIG)
    abstract fun getConfig(): Single<Config>
}