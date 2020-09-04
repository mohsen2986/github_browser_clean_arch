package com.remote.model

import com.google.gson.annotations.SerializedName

class ProjectModel(
    @SerializedName("id")
    val id: String ,
    @SerializedName("name")
    val name: String ,
    @SerializedName("full_name")
    val fullName: String ,
    @SerializedName("stargazers_count")
    val starCount: Int ,
    @SerializedName("created_at")
    val dateCreated: String ,
    @SerializedName("owner")
    val owner: OwnerModel
)