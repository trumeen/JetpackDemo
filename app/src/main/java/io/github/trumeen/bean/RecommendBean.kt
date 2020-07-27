package io.github.trumeen.bean

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import io.github.trumeen.ui.eyepetizer.video.VideoPlayerActivity
import kotlinx.android.parcel.Parcelize

const val VIDEO_INFO: String = "videoInfo"

data class RecommendBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<RecommendItemBean>,
    val nextPageUrl: String,
    val total: Int
)

data class RecommendItemBean(
    val adIndex: Int,
    val `data`: RecommendData,
    val id: Int,
    val tag: Any,
    val type: String
) {
    fun goToPage(context: Context, url: String?) {
        println("context:$context url:$url")
        if (url.isNullOrBlank()) {
            return
        }
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun goToVideoPage(context: Context, videoInfo: DataX) {

        val videoIntent = Intent(context, VideoPlayerActivity::class.java)
        videoIntent.putExtra(
            VIDEO_INFO, VideoInfoBean(
                videoInfo.id,
                videoInfo.playUrl,
                videoInfo.cover.detail,
                videoInfo.title,
                videoInfo.description,
                videoInfo.category,
                videoInfo.consumption,
                videoInfo.author
            )
        )
        context.startActivity(videoIntent)
    }
}

data class RecommendData(
    val actionUrl: String,
    val weeklyDestination: String,
    val dailyDestination: String,
    val recReason: String,
    val posterTitle: String,
    val image: String,
    val imageUrl: String,
    val ad: Boolean,
    val adTrack: Any,
    val author: Author,
    val backgroundImage: String,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val content: Content,
    val count: Int,
    val cover: CoverX,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: Any,
    val duration: Int,
    val expert: Boolean,
    val favoriteAdTrack: Any,
    val follow: Any,
    val footer: Any,
    val haveReward: Boolean,
    val header: Header,
    val icon: String,
    val iconType: String,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val ifNewest: Boolean,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val itemList: List<RecommendItemBean>,
    val label: Any,
    val labelList: List<Any>,
    val lastViewTime: Any,
    val library: String,
    val medalIcon: Boolean,
    val newestEndTime: Any,
    val playInfo: List<PlayInfoX>,
    val playUrl: String,
    val played: Boolean,
    val playlists: Any,
    val promotion: Any,
    val provider: ProviderX,
    val reallyCollected: Boolean,
    val recallSource: String,
    val refreshUrl: String,
    val releaseTime: Long,
    val remark: Any,
    val resourceType: String,
    val rightText: String,
    val searchWeight: Int,
    val shareAdTrack: Any,
    val showHotSign: Boolean,
    val showImmediately: Boolean,
    val slogan: Any,
    val src: Int,
    val subTitle: Any,
    val subtitles: List<Any>,
    val switchStatus: Boolean,
    val tags: List<TagX>,
    val text: String,
    val thumbPlayUrl: Any,
    val title: String,
    val titleList: List<String>,
    val titlePgc: Any,
    val topicTitle: String,
    val type: String,
    val uid: Int,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: WebUrlX
)

@Parcelize
data class Author(
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
) : Parcelable

@Parcelize
data class Consumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
) : Parcelable

data class Content(
    val adIndex: Int,
    val `data`: DataX,
    val id: Int,
    val tag: Any,
    val type: String
)

data class CoverX(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String,
    val sharing: Any
)

data class Header(
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
    val rightText: String,
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

@Parcelize
data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
) : Parcelable

@Parcelize
data class Shield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
) : Parcelable

data class DataX(
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
    val descriptionPgc: String,
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
    val slogan: String,
    val src: Any,
    val status: Any,
    val subtitles: List<Any>,
    val tags: List<DataXTag>,
    val thumbPlayUrl: String,
    val title: String,
    val titlePgc: String,
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

data class AuthorX(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: FollowX,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: ShieldX,
    val videoNum: Int
)

data class ConsumptionX(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class Cover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String,
    val sharing: Any
)

data class PlayInfo(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<Url>,
    val width: Int
)

data class RecentOnceReply(
    val actionUrl: String,
    val contentType: Any,
    val dataType: String,
    val message: String,
    val nickname: String
)

data class Provider(
    val alias: String,
    val icon: String,
    val name: String
)

data class DataXTag(
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

data class WebUrl(
    val forWeibo: String,
    val raw: String
)

data class FollowX(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class ShieldX(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class Url(
    val name: String,
    val size: Int,
    val url: String
)

data class DataXX(
    val cover: CoverXX,
    val dailyResource: Boolean,
    val dataType: String,
    val id: Int,
    val nickname: String,
    val playUrl: String,
    val resourceType: String,
    val url: String,
    val urls: List<String>,
    val userCover: String
)

data class CoverXX(
    val blurred: Any,
    val detail: String,
    val feed: String,
    val homepage: Any,
    val sharing: Any
)

data class UrlX(
    val name: String,
    val size: Int,
    val url: String
)


data class ItemX(
    val adIndex: Int,
    val `data`: DataXX,
    val id: Int,
    val tag: Any,
    val type: String
)

data class PlayInfoX(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<UrlX>,
    val width: Int
)

data class ProviderX(
    val alias: String,
    val icon: String,
    val name: String
)

data class TagX(
    val actionUrl: String,
    val adTrack: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: Any,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val newestEndTime: Any,
    val tagRecType: String
)

data class WebUrlX(
    val forWeibo: String,
    val raw: String
)