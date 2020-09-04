package com.remote

import com.data.model.ProjectEntity
import com.nhaarman.mockitokotlin2.*
import com.remote.mapper.ProjectsResponseModelMapper
import com.remote.model.ProjectModel
import com.remote.model.ProjectsResponseModel
import com.remote.service.GithubTrendingService
import com.remote.test.factory.ProjectDataFactory
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteImplTest{

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectCompletes(){

    }
    @Test
    fun getProjectsCallServer() {
        stubGithubTrendingServiceSearchRepositories(
            Flowable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Flowable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsWithCorrectParameter() {
        stubGithubTrendingServiceSearchRepositories(
            Flowable.just(
            ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(QUERY, SORT_BY, ORDER)
    }

    private fun stubGithubTrendingServiceSearchRepositories(stub: Flowable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any())) doReturn stub
    }

    private fun stubProjectsResponseModelMapperFromModel(model: ProjectModel, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model)) doReturn entity
    }
}