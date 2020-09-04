package com.example.cache

import com.data.model.ProjectEntity
import com.data.repository.ProjectsCache
import com.example.cache.db.ProjectsDatabase
import com.example.cache.mapper.CacheProjectMapper
import com.example.cache.model.Config
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCachedImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val mapper: CacheProjectMapper
):ProjectsCache{
    override fun clearProjects(): Completable {
        return Completable.defer{
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer{
            projectsDatabase.cachedProjectsDao().insertProjects(
                projects.map{ mapper.mapToCached(it)})
            Completable.complete()
        }
    }

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
            .map { list -> list.map { mapper.mapFromCached(it) } }
    }

    override fun getBookmarkProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao()
            .getBookmarkedProjects()
            .toObservable()
            .map{list -> list.map { mapper.mapFromCached(it) }}
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer{
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false , projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer{
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true , projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao()
            .cachedProjectsExist()
            .map{ it > 0}
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer{
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val current = System.currentTimeMillis()
        val expired = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
            .onErrorReturn { Config(lastCacheTime = 0) }
            .map { current - it.lastCacheTime > expired }
    }

}