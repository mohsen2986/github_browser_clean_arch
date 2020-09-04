package com.remote

import com.data.model.ProjectEntity
import com.data.repository.ProjectsRemote
import com.remote.mapper.ProjectsResponseModelMapper
import com.remote.service.GithubTrendingService
import io.reactivex.Flowable
import javax.inject.Inject

const val QUERY = "language:kotlin"
const val SORT_BY = "stars"
const val ORDER = "desc"

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
): ProjectsRemote{
    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return service.searchRepositories(QUERY , SORT_BY , ORDER)
            .map { response -> response.items.map { mapper.mapFromModel(it) } }
    }

}