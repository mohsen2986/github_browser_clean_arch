package com.domain.model.browse

import com.domain.executer.PostExecutionThread
import com.domain.interactor.ObservableUsecase
import com.domain.model.Project
import com.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository ,
    postExecutionThread: PostExecutionThread
): ObservableUsecase<List<Project>, Nothing>(postExecutionThread){
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}