package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cache.db.ProjectConstants.DELETE_PROJECTS
import com.example.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import com.example.cache.db.ProjectConstants.QUERY_EXISTS
import com.example.cache.db.ProjectConstants.QUERY_PROJECTS
import com.example.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import com.example.cache.model.CachedProject
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class CachedProjectsDao{

    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_EXISTS)
    abstract fun cachedProjectsExist(): Single<Int>

    @Insert(onConflict = REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean , projectId: String)
}