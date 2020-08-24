package com.domain.repository

import com.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsRepository{

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unBookmarkProject(projectId: String): Completable

    fun getBookmarkProject(): Observable<List<Project>>
}