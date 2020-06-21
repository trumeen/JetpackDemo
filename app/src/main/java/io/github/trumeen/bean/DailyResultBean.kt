package io.github.trumeen.bean

data class DailyResultBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
)

data class Data(
    val actionUrl: Any,
    val adTrack: Any,
    val content: DailyContent,
    val dataType: String,
    val follow: Any,
    val header: DialyHeader,
    val id: Int,
    val subTitle: Any,
    val text: String,
    val type: String
)

data class DailyContent(
    val adIndex: Int,
    val `data`: DailyDataX,
    val id: Int,
    val tag: Any,
    val type: String
)

data class DialyHeader(
    val actionUrl: String,
    val cover: Any,
    val description: String,
    val followType: String,
    val font: Any,
    val icon: String,
    val iconType: String,
    val id: Int,
    val issuerName: String,
    val label: Any,
    val labelList: Any,
    val rightText: Any,
    val showHateVideo: Boolean,
    val subTitle: Any,
    val subTitleFont: Any,
    val tagId: Int,
    val tagName: Any,
    val textAlign: String,
    val time: Long,
    val title: String,
    val topShow: Boolean
)

data class DailyDataX(
    val ad: Boolean,
    val adTrack: List<Any>,
    val addWatermark: Boolean,
    val area: String,
    val author: Author,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val checkStatus: String,
    val city: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val createTime: Long,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: Any,
    val duration: Int,
    val favoriteAdTrack: Any,
    val height: Int,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val ifMock: Boolean,
    val label: Any,
    val labelList: List<Any>,
    val lastViewTime: Any,
    val latitude: Double,
    val library: String,
    val longitude: Double,
    val owner: Owner,
    val playInfo: List<PlayInfo>,
    val playUrl: String,
    val playUrlWatermark: String,
    val played: Boolean,
    val playlists: Any,
    val privateMessageActionUrl: Any,
    val promotion: Any,
    val provider: Provider,
    val reallyCollected: Boolean,
    val recallSource: Any,
    val recentOnceReply: RecentOnceReply,
    val releaseTime: Long,
    val remark: Any,
    val resourceType: String,
    val searchWeight: Int,
    val selectedTime: Long,
    val shareAdTrack: Any,
    val slogan: Any,
    val src: Any,
    val status: Any,
    val subtitles: List<Any>,
    val tags: List<Tag>,
    val thumbPlayUrl: Any,
    val title: String,
    val titlePgc: Any,
    val transId: Any,
    val type: String,
    val uid: Int,
    val updateTime: Long,
    val url: String,
    val urls: List<String>,
    val urlsWithWatermark: List<String>,
    val validateResult: String,
    val validateStatus: String,
    val validateTaskId: String,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: WebUrl,
    val width: Int
)


data class Owner(
    val actionUrl: String,
    val area: Any,
    val avatar: String,
    val birthday: Long,
    val city: Any,
    val country: String,
    val cover: String,
    val description: String,
    val expert: Boolean,
    val followed: Boolean,
    val gender: String,
    val ifPgc: Boolean,
    val job: Any,
    val library: String,
    val limitVideoOpen: Boolean,
    val nickname: String,
    val registDate: Long,
    val releaseDate: Long,
    val uid: Int,
    val university: Any,
    val userType: String
)



data class RecentOnceReply(
    val actionUrl: String,
    val contentType: Any,
    val dataType: String,
    val message: String,
    val nickname: String
)

data class Tag(
    val actionUrl: String,
    val adTrack: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val newestEndTime: Long,
    val tagRecType: String
)

