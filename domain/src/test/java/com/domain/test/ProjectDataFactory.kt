package com.domain.test

import com.domain.model.Project
import java.util.*

object ProjectDataFactory{
    fun randomUuid(): String{
        return UUID.randomUUID().toString()
    }
    fun randomBoolean(): Boolean =
        Math.random() <0.5

    fun makeProject():Project =
        Project(randomUuid() , randomUuid() , randomUuid()
            , randomUuid() , randomUuid() , randomUuid()
            , randomUuid() , randomBoolean())

    fun makeProjectList(count:Int):List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}