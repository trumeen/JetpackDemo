package io.github.trumeen.bean

data class CourseContentBean(
    val results: List<BmobCourseContentResult>
)

data class BmobCourseContentResult(
    val createdAt: String,
    val id: String,
    val objectId: String,
    val result: String,
    val updatedAt: String
)