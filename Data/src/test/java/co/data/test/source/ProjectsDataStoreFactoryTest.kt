package co.data.test.source

import com.data.source.ProjectsCacheDataSource
import com.data.source.ProjectsRemoteDataSource
import com.data.source.ProjectsDataSourceFactory
import com.nhaarman.mockitokotlin2.mock
import kotlin.test.assertEquals
import org.junit.Test

class ProjectsDataStoreFactoryTest{

    private val cacheStore = mock<ProjectsCacheDataSource>()
    private val remoteStore = mock<ProjectsRemoteDataSource>()
    private val factory = ProjectsDataSourceFactory(cacheStore , remoteStore)

    @Test
    fun getDataSourceRemoteStoreWhenCacheExpired(){
        assertEquals(remoteStore , factory.getDataSource(true , true))
    }
    @Test
    fun getDataSourceReturnsRemoteStoreWhenProjectsNotCached(){
        assertEquals(remoteStore , factory.getDataSource(false , false))
    }
    @Test
    fun getDataSourceReturnsCachedStore(){
        assertEquals(cacheStore , factory.getDataSource(true , false))
    }
    @Test
    fun getCacheDataSourceReturnsCacheStore(){
        assertEquals(cacheStore , factory.getCacheDataSource())
    }

}