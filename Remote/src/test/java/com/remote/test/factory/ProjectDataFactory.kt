package com.remote.test.factory

import com.data.model.ProjectEntity
import com.remote.model.OwnerModel
import com.remote.model.ProjectModel
import com.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    private fun makeOwner(): OwnerModel {
        return with(DataFactory) {
            OwnerModel(randomString(), randomString())
        }
    }

    fun makeProject(): ProjectModel {
        return with(DataFactory) {
            ProjectModel(randomString(), randomString(), randomString(),
                randomInt(), randomString(), makeOwner())
        }
    }

    fun makeProjectEntity(): ProjectEntity {
        return with(DataFactory) {
            ProjectEntity(randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), randomString(), randomBoolean())
        }
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}
