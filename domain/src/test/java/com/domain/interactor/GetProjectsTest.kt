package com.domain.interactor

import com.domain.executer.PostExecutionThread
import com.domain.model.Project
import com.domain.model.browse.GetProjects
import com.domain.repository.ProjectsRepository
import com.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

class GetProjectsTest{

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository , postExecutionThread)
    }
    @Test
    fun getProjectsCompletes(){
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver =  getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }
    @Test
    fun getProjectsReturnsData(){
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))
        val testObserver =  getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }
    private fun stubGetProjects(observable: Observable<List<Project>>){
        whenever(projectsRepository.getProjects())
            .thenReturn(observable)
    }

}