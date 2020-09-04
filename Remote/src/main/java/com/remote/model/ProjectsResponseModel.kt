package com.remote.model

import com.google.gson.annotations.SerializedName

class ProjectsResponseModel(
    @SerializedName("items")
    val items: List<ProjectModel>
)