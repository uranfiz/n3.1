data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val donut: Donut = Donut(),
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: List<Attachment> = emptyList(),
    val parentsStack: List<Int> = emptyList(),
    val thread: Thread = Thread()
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeholder: String = "",
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = ""
)

data class Thread(
    val count: Int = 0,
    val items: List<Comment> = emptyList(),
    val canPost: Boolean = true,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = true
)
