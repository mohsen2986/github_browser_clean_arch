package com.presentation.mapper

import com.presentation.test.factory.ProjectFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProjectViewMapperTest{
    private val mapper = ProjectViewMapper()

    @Test
    fun mapToViewMapsData(){
        val project = ProjectFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id , projectView.id)
        assertEquals(project.name , projectView.name)
        assertEquals(project.fullName , projectView.fullName)
        assertEquals(project.starCount , projectView.starCount)
        assertEquals(project.dateCreated , projectView.dateCreated)
        assertEquals(project.ownName , projectView.ownerName)
        assertEquals(project.ownerAvatar , projectView.ownerAvatar)
        assertEquals(project.isBookmarcked , projectView.isBookmarked)
    }
}