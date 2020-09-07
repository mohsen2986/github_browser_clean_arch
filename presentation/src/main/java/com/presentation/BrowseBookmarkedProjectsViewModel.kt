package com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.Project
import com.domain.model.bookmark.GetBookmarkProjects
import com.presentation.mapper.ProjectViewMapper
import com.presentation.model.ProjectView
import com.presentation.state.Resource
import com.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkProjects: GetBookmarkProjects ,
    private val mapper: ProjectViewMapper
):ViewModel(){

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkProjects.dispose()
        super.onCleared()
    }

    fun getProjects() : LiveData<Resource<List<ProjectView>>>{
        return liveData
    }


    fun fetchProjects(){
        liveData.postValue(Resource(ResourceState.LOADING))
        getBookmarkProjects.execute(ProjectSubscriber())
    }
    inner class ProjectSubscriber :DisposableObserver<List<Project>>(){
        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(
                ResourceState.SUCCESS ,
                data = t.map { mapper.mapToView(it) }
            ))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(
                ResourceState.ERROR ,
                message = e.localizedMessage
            ))
        }

    }
}