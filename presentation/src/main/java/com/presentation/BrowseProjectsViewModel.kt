package com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.Project
import com.domain.model.bookmark.BookmarkProject
import com.domain.model.bookmark.UnBookmarkProject
import com.domain.model.browse.GetProjects
import com.presentation.mapper.ProjectViewMapper
import com.presentation.model.ProjectView
import com.presentation.state.Resource
import com.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val bookmarkProject: BookmarkProject,
    private val unBookmarkProject: UnBookmarkProject ,
    private val mapper: ProjectViewMapper
):ViewModel(){

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }
    fun getProjects(): LiveData<Resource<List<ProjectView>>>{
        return liveData
    }
    fun fetchProjects(){
        liveData.postValue(Resource(ResourceState.LOADING))
        getProjects.execute(ProjectSubscriber())
    }
    fun bookmarkProject(projectId: String){
        liveData.postValue(Resource(ResourceState.LOADING))
        bookmarkProject.execute(
            BookmarkProjectSubscriber() ,
            BookmarkProject.Params.forProject(projectId)
        )
    }
    fun unBookmarkProject(projectId: String){
        liveData.postValue(Resource(ResourceState.LOADING))
        unBookmarkProject.execute(
            BookmarkProjectSubscriber() ,
            UnBookmarkProject.Params.forProject(projectId)
        )
    }

    inner class ProjectSubscriber : DisposableObserver<List<Project>>(){
        override fun onNext(t: List<Project>) {
            liveData.postValue(
                Resource(ResourceState.SUCCESS ,
                t.map{ mapper.mapToView(it)})
                )
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(
                Resource(ResourceState.ERROR ,
                message = e.localizedMessage)
            )
        }

    }
    inner class BookmarkProjectSubscriber: DisposableCompletableObserver(){
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR ,
            message = e.localizedMessage))
        }

    }
}