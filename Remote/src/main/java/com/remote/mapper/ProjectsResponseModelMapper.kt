package com.remote.mapper

import com.remote.model.ProjectModel
import com.data.model.ProjectEntity
import javax.inject.Inject

class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel , ProjectEntity>{
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return with(model){
            ProjectEntity(
                    id , name , fullName , starCount.toString() ,
                    dateCreated , owner.ownerName , owner.ownerAvatar , false
            )
        }
    }

}