import java.util.ArrayList;
import java.util.List;

public class Post {
    private static int idCounter = 1;

    private int postId;
    private Topic topic;
    private User user;
    private String text;
    private String picture;

    private List<Comment> comments;

    public Post(Topic topic, User user, String text, String picture) {
        this.postId = idCounter++;
        this.topic = topic;
        this.user = user;
        this.text = text;
        this.picture = picture;
        this.comments = new ArrayList<>();
    }

    public int getPostId() { return postId; }
    public Topic getTopic() { return topic; }
    public User getUser() { return user; }
    public String getText() { return text; }

    public List<Comment> getComments() { return comments; }
    public void addComment(Comment comment) { this.comments.add(comment); }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", topic=" + topic.getTopicName() + ", text='" + text + '\'' + '}';
    }
}