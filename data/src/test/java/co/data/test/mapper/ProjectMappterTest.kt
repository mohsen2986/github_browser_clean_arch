package co.data.test.mapper

import co.data.test.factory.ProjectFactory
import com.data.mapper.ProjectMapper
import com.data.model.ProjectEntity
import com.domain.model.Project
import org.junit.Test

class ProjectMapperTest{

    private val mapper = ProjectMapper()

    @Test
    fun mapFromEntityMapsData(){
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualsData(entity , model)
    }
    @Test
    fun maoToEntityMapsData(){
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)
        assertEqualsData(entity , model)
    }
    private fun assertEqualsData(entity: ProjectEntity,  model : Project) {
        kotlin.test.assertEquals(entity.id, model.id)
        kotlin.test.assertEquals(entity.name, model.name)
        kotlin.test.assertEquals(entity.fullName, model.fullName)
        kotlin.test.assertEquals(entity.starCount, model.starCount)
        kotlin.test.assertEquals(entity.dateCreated, model.dateCreated)
        kotlin.test.assertEquals(entity.ownName, model.ownName)
        kotlin.test.assertEquals(entity.ownerAvatar, model.ownerAvatar)
    }

}