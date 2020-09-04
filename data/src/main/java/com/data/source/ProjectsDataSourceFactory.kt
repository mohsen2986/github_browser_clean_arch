package com.data.source

import com.data.repository.ProjectsDataSource
import javax.inject.Inject

open class ProjectsDataSourceFactory @Inject constructor(
    private val projectsCacheDataSource: ProjectsCacheDataSource ,
    private val projectsRemoteDataSource: ProjectsRemoteDataSource
){
    open fun getDataSource(projectsCached: Boolean , cacheExpired: Boolean):ProjectsDataSource{
        return if(projectsCached && !cacheExpired){
            projectsCacheDataSource
        }else{
            projectsRemoteDataSource
        }
    }
    open fun getCacheDataSource(): ProjectsDataSource{
        return projectsCacheDataSource
    }
}