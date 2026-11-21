class WallService {

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Report>()
    private var nextPostId = 1
    private var nextCommentId = 1
    private var nextReportId = 1

    fun add(post: Post): Post {
        val newPost = post.copy(id = nextPostId++)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, p) in posts.withIndex()) {
            if (p.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun getById(id: Int): Post? = posts.find { it.id == id }
    fun getAll(): List<Post> = posts.toList()
    fun createComment(postId: Int, comment: Comment): Comment {
        val postExists = posts.any { it.id == postId }
        if (!postExists) {
            throw PostNotFoundException("Пост с id=$postId не найден")
        }
        val newComment = comment.copy(id = nextCommentId++)
        comments += newComment
        return newComment
    }
    fun getComments(): List<Comment> = comments.toList()
    fun getCommentsByPost(postId: Int): List<Comment> =
        comments.filter { it.fromId == postId }

    fun createReport(postId: Int, commentId: Int, reason: ReportReason): Report {
        if (posts.none { it.id == postId }) {
            throw PostNotFoundException("Пост с id=$postId не найден")
        }
        if (comments.none { it.id == commentId }) {
            throw CommentNotFoundException("Комментарий с id=$commentId не найден")
        }
        if (reports.any { it.commentId == commentId }) {
            throw InvalidReportException("Жалоба на комментарий id=$commentId уже подана")
        }
        val report = Report(
            id = nextReportId++,
            commentId = commentId,
            postId = postId,
            reason = reason
        )
        reports += report
        return report
    }

    fun getReports(): List<Report> = reports.toList()
}
