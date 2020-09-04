package com.data.repository

import com.data.model.ProjectEntity
import io.reactivex.Flowable

interface ProjectsRemote{
    fun getProjects(): Flowable<List<ProjectEntity>>
}