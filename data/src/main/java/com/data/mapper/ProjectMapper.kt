package com.data.mapper

import com.data.model.ProjectEntity
import com.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor(): EntityMapper<ProjectEntity, Project>{
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return with(entity){
            Project(id , name , fullName , starCount , dateCreated ,
                    ownName , ownerAvatar , isBookmarked)
        }
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return with(domain){
            ProjectEntity(id , name , fullName , starCount , dateCreated ,
                        ownName , ownerAvatar , isBookmarcked)
        }
    }

}