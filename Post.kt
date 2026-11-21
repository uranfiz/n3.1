data class Post(
    val id: Int,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: CommentsInfo = CommentsInfo(),
    val likes: LikesInfo = LikesInfo(),
    val reposts: RepostsInfo = RepostsInfo(),
    val views: ViewsInfo = ViewsInfo(),
    val postType: String = "post",
    val attachments: List<Attachment> = emptyList(),
    val canDelete: Boolean = true
)

data class CommentsInfo(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class LikesInfo(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class RepostsInfo(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class ViewsInfo(
    val count: Int = 0
)

data class Attachment(
    val type: String = "",
    val url: String = ""
)
