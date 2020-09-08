package com.example.cache.mapper

import com.data.model.ProjectEntity
import com.example.cache.model.CachedProject
import com.example.cache.test.factory.ProjectDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedProjectMapperTest {

    private val mapper = CacheProjectMapper()

    @Test fun mapFromCachedMapsData() {
        val model = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromCached(model)

        assertEqualsData(model, entity)
    }

    @Test fun mapToCacheMapsData() {
        val entity = ProjectDataFactory.makeProjectEntity()
        val model = mapper.mapToCached(entity)

        assertEqualsData(model, entity)
    }

    private fun assertEqualsData(model: CachedProject, entity: ProjectEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.name, entity.name)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.starCount, entity.starCount)
        assertEquals(model.isBookmarked, entity.isBookmarked)
        assertEquals(model.ownerName, entity.ownName)
        assertEquals(model.ownerAvatar, entity.ownerAvatar)
    }
}
