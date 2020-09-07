package com.presentation.bookmarked

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.domain.model.Project
import com.domain.model.bookmark.GetBookmarkProjects
import com.nhaarman.mockitokotlin2.*
import com.presentation.BrowseBookmarkedProjectsViewModel
import com.presentation.mapper.ProjectViewMapper
import com.presentation.model.ProjectView
import com.presentation.state.ResourceState
import com.presentation.test.factory.DataFactory
import com.presentation.test.factory.ProjectFactory
import io.reactivex.observers.DisposableObserver
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals


class BrowseBookmarkedProjectsViewModelTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val getBookmarkedProjects: GetBookmarkProjects = mock()
    private val mapper: ProjectViewMapper = mock()
    private val projectViewModel = BrowseBookmarkedProjectsViewModel(getBookmarkedProjects, mapper)
    private val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsExecutesUseCase() {
        projectViewModel.fetchProjects()
        verify(getBookmarkedProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetProjectsReturnsSuccess() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projects[0], projectViews[0])
        stubProjectMapperMapToView(projects[1], projectViews[1])
        projectViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)
        assertEquals(ResourceState.SUCCESS, projectViewModel.getProjects().value?.status)
    }

    @Test
    fun fetProjectsReturnsData() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projects[0], projectViews[0])
        stubProjectMapperMapToView(projects[1], projectViews[1])
        projectViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)
        assertEquals(projectViews, projectViewModel.getProjects().value?.data)
    }

    @Test
    fun fetProjectsReturnsError() {
        projectViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        assertEquals(ResourceState.ERROR, projectViewModel.getProjects().value?.status)
    }

    @Test
    fun fetProjectsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        projectViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))
        assertEquals(errorMessage, projectViewModel.getProjects().value?.message)
    }

    private fun stubProjectMapperMapToView(project: Project, projectView: ProjectView) {
        whenever(mapper.mapToView(project)) doReturn projectView
    }
}