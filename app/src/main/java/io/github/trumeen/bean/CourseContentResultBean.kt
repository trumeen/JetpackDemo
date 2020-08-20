package io.github.trumeen.bean

data class CourseContentResultBean(
    val contentItems: List<ContentItem>,
    val currentPage: Int,
    val session: Session,
    val teacher: Teacher,
    val total: Int
)

data class ContentItem(
    val audioUrl: String,
    val contentType: Int,
    val duration: Int,
    val id: String,
    val imageUrl: String,
    val resourceDurationInMs: Int,
    val sequence: Int,
    val startPlayOffset: Int,
    val textContent: String
)

data class Session(
    val allowAnonymous: Boolean,
    val name: String,
    val serverTime: Int,
    val started: Boolean,
    val startedAtSec: Int,
    val totalDuration: Int
)

data class Teacher(
    val avatar: String,
    val id: String,
    val nick: String
)