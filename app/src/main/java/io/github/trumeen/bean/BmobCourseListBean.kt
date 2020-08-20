package io.github.trumeen.bean

data class BmobCourseListBean(
    val results: List<Course>
)

data class Result(
    val createdAt: String,
    val id: String,
    val level: Int,
    val objectId: String,
    val startTime: Int,
    val status: Int,
    val tag: Int,
    val title: String,
    val type: Int,
    val updatedAt: String,
    val uri: String
)