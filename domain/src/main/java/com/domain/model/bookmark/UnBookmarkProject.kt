package com.domain.model.bookmark

import com.domain.executer.PostExecutionThread
import com.domain.interactor.CompletableUserCase
import com.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

class UnBookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository ,
    postExecutionThread: PostExecutionThread
): CompletableUserCase<UnBookmarkProject.Params>(postExecutionThread){

    data class Params constructor(val projectId: String){
        companion object{
            fun forProject(projectId: String):Params{
                return Params(projectId)
            }
        }
    }

    public override fun buildUserCaseObservable(params: Params?): Completable {
        if(params == null) throw IllegalArgumentException("params cant be null!!")
        return projectsRepository.unBookmarkProject(params.projectId)
    }
}