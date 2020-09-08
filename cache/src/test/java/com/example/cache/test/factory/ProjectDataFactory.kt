package com.example.cache.test.factory

import com.data.model.ProjectEntity
import com.example.cache.model.CachedProject


object ProjectDataFactory {

    fun makeCachedProject(): CachedProject = with(DataFactory) {
        CachedProject(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), false)
    }

    fun makeCachedBookmarkedProject(): CachedProject = with(DataFactory) {
        CachedProject(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), true)
    }

    fun makeProjectEntity(): ProjectEntity = with(DataFactory) {
        ProjectEntity(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), false)
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity = with(DataFactory) {
        ProjectEntity(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), true)
    }
}
