package com.domain.model.bookmark

import com.domain.executer.PostExecutionThread
import com.domain.interactor.ObservableUsecase
import com.domain.model.Project
import com.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository ,
    postExecutionThread: PostExecutionThread
):ObservableUsecase<List<Project> , Nothing>(postExecutionThread){
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkProject()
    }

}