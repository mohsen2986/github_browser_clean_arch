package com.example.cache.mapper

import com.data.model.ProjectEntity
import com.example.cache.model.CachedProject

class CacheProjectMapper:CacheMapper<CachedProject, ProjectEntity>{

    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return with(type){
            ProjectEntity(id , name , fullName , starCount , dateCreated , ownerName , ownerAvatar , isBookmarked)
        }
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return with(type){
            CachedProject(id , name , fullName , starCount , dateCreated , ownName , ownerAvatar , isBookmarked)
        }
    }

}