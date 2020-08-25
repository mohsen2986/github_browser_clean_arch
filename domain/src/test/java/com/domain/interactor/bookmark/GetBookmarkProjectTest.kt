package com.domain.interactor.bookmark

import com.domain.executer.PostExecutionThread
import com.domain.model.Project
import com.domain.model.bookmark.GetBookmarkProjects
import com.domain.repository.ProjectsRepository
import com.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkProjectTest{
    private lateinit var getBookmarkProjects: GetBookmarkProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getBookmarkProjects = GetBookmarkProjects(projectsRepository , postExecutionThread)
    }
    @Test
    fun getBookmarkedProjectsCompletes(){
        stupGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }
    @Test
    fun getBookmarkedProjectsReturnsData(){
        val projects = ProjectDataFactory.makeProjectList(2)
        stupGetProjects(Observable.just(projects))
        val testObserver = getBookmarkProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }
    private fun stupGetProjects(observable: Observable<List<Project>>){
        whenever(projectsRepository.getBookmarkProject())
                .thenReturn(observable)
    }
}