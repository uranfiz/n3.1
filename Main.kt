fun main() {
    val service = WallService()
    val post = service.add(Post(id = 0, text = "мой первый пост"))
    val comment = service.createComment(post.id, Comment(text = "отличный пост"))
    println("Добавлен комментарий: $comment")
}
