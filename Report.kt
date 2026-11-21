data class Report(
    val id: Int = 0,
    val commentId: Int,
    val postId: Int,
    val reason: ReportReason,
    val createdAt: Long = System.currentTimeMillis()
)

enum class ReportReason {
    SPAM,
    ABUSE,
    VIOLENCE,
    OTHER
}
