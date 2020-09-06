package com.presentation.mapper

import com.domain.model.Project
import com.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor():Mapper<ProjectView, Project>{
    override fun mapToView(type: Project): ProjectView {
        return with(type){
            ProjectView(
                id , name , fullName , starCount , dateCreated , ownName ,
                ownerAvatar , isBookmarcked
            )
        }
    }

}