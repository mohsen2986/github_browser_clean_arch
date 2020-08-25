package com.domain.interactor.bookmark

import com.domain.executer.PostExecutionThread
import com.domain.model.bookmark.UnBookmarkProject
import com.domain.repository.ProjectsRepository
import com.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkProjectTest{

    private lateinit var unBookmarkProject : UnBookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        unBookmarkProject = UnBookmarkProject(projectsRepository , postExecutionThread)
    }
    @Test
    fun unBookmarkProjectsComplete(){
        stbUnBookmarkProjectCompletes(Completable.complete())
        val testObserver = unBookmarkProject.buildUserCaseObservable(
                UnBookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }
    @Test(expected = IllegalArgumentException::class)
    fun unBookmarkProjectThrowsException(){
        unBookmarkProject.buildUserCaseObservable().test()
    }
    private fun stbUnBookmarkProjectCompletes(completable: Completable){
        whenever(projectsRepository.unBookmarkProject(any()))
                .thenReturn(completable)
    }
}