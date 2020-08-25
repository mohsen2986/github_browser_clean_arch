package com.domain.interactor.bookmark

import com.domain.executer.PostExecutionThread
import com.domain.model.bookmark.BookmarkProject
import com.domain.repository.ProjectsRepository
import com.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectTest{

    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository , postExecutionThread)
    }
    @Test
    fun bookmarkProjectCompletes(){
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProject.buildUserCaseObservable(
                BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }
    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException(){
        bookmarkProject.buildUserCaseObservable().test()
    }


    private fun stubBookmarkProject(completable: Completable){
        whenever(projectsRepository.bookmarkProject(any()))
                .thenReturn(completable)
    }
}