import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentRepository {
    private List<Comment> comments = new ArrayList<>();

    // Метод для добавления комментария
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Метод для получения всех комментариев
    public List<Comment> getAllComments() {
        return new ArrayList<>(comments);
    }

    // Метод для получения комментариев по ID поста
    public List<Comment> findByPostId(int postId) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == postId)
                .collect(Collectors.toList());
    }

    // Метод для получения комментариев определенного пользователя
    public List<Comment> findByUserId(int userId) {
        return comments.stream()
                .filter(comment -> comment.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
