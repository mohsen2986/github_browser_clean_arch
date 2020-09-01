package com.data

import com.data.mapper.ProjectMapper
import com.data.repository.ProjectsCache
import com.data.source.ProjectsDataSourceFactory
import com.domain.model.Project
import com.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectDataRepository @Inject constructor(
    private val mapper: ProjectMapper ,
    private val cache: ProjectsCache ,
    private val factory: ProjectsDataSourceFactory
): ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(
            cache.areProjectsCached().toObservable(),
            cache.isProjectsCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { cached, expired ->
                Pair(cached, expired)
            }).flatMap {
            factory.getDataSource(it.first, it.second)
                .getProjects()
                .toObservable()
                .distinctUntilChanged()
        }.flatMap { projects ->
            factory.getCacheDataSource()
                .saveProjects(projects)
                .andThen(
                    factory.getCacheDataSource()
                        .getProjects()
                        .toObservable()
                )
        }.map { list -> list.map { mapper.mapFromEntity(it) } }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataSource().setProjectAsBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCacheDataSource().setProjectAsNotBookmarked(projectId )
    }

    override fun getBookmarkProject(): Observable<List<Project>> {
        return factory.getCacheDataSource().getBookmarkedProjects().
                map { list -> list.map { mapper.mapFromEntity(it) } }
    }

}