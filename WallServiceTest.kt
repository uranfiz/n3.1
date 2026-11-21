import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WallServiceTest {

    @Test
    fun shouldAddCommentToExistingPost() {
        val service = WallService()
        val post = service.add(Post(id = 0, text = "Пост"))

        val comment = service.createComment(post.id, Comment(text = "Комментарий"))

        assertEquals(1, comment.id)
        assertEquals("Комментарий", comment.text)
        assertEquals(1, service.getComments().size)
        assertTrue(service.getComments().contains(comment))
    }

    @Test
    fun shouldThrowWhenAddingCommentToNonExistingPost() {
        val service = WallService()
        assertThrows<PostNotFoundException> {
            service.createComment(999, Comment(text = "Комментарий"))
        }
    }

    @Test
    fun shouldThrowWithExpectedAnnotation() {
        try {
            val service = WallService()
            service.createComment(42, Comment(text = "Комментарий"))
            fail("Ожидалось исключение PostNotFoundException")
        } catch (e: PostNotFoundException) {
        }
    }


    @Test
    fun shouldCreateReportForExistingComment() {
        val service = WallService()
        val post = service.add(Post(id = 0, text = "Пост"))
        val comment = service.createComment(post.id, Comment(text = "Плохой комментарий"))

        val report = service.createReport(post.id, comment.id, ReportReason.ABUSE)

        assertEquals(1, report.id)
        assertEquals(comment.id, report.commentId)
        assertEquals(1, service.getReports().size)
    }

    @Test
    fun shouldThrowWhenReportingNonExistingPost() {
        val service = WallService()
        assertThrows<PostNotFoundException> {
            service.createReport(12345, 1, ReportReason.SPAM)
        }
    }

    @Test
    fun shouldThrowWhenReportingNonExistingComment() {
        val service = WallService()
        val post = service.add(Post(id = 0, text = "Пост"))
        assertThrows<CommentNotFoundException> {
            service.createReport(post.id, 999, ReportReason.SPAM)
        }
    }

    @Test
    fun shouldThrowWhenReportingSameCommentTwice() {
        val service = WallService()
        val post = service.add(Post(id = 0, text = "Пост"))
        val comment = service.createComment(post.id, Comment(text = "Спам"))

        service.createReport(post.id, comment.id, ReportReason.SPAM)

        assertThrows<InvalidReportException> {
            service.createReport(post.id, comment.id, ReportReason.SPAM)
        }
    }
}
