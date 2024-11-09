import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    // Метод для добавления поста
    public void addPost(Post post) {
        posts.add(post);
    }

    // Метод для получения всех постов
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }

    // Метод для получения постов по ID категории
    public List<Post> findByTopicId(int topicId) {
        return posts.stream()
                .filter(post -> post.getTopicId() == topicId)
                .collect(Collectors.toList());
    }

    // Метод для получения постов определенного пользователя
    public List<Post> findByUserId(int userId) {
        return posts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
