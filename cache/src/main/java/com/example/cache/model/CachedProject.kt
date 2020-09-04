package com.example.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.db.ProjectConstants.COLUMN_IS_BOOKMARKED
import com.example.cache.db.ProjectConstants.COLUMN_PROJECT_ID
import com.example.cache.db.ProjectConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CachedProject(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_PROJECT_ID)
    val id: String ,
    var name: String,
    var fullName: String,
    var starCount: String,
    var dateCreated: String,
    var ownerName: String,
    var ownerAvatar: String,
    @ColumnInfo(name = COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)