import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    private static int idCounter = 1;

    private int userId;
    private String userName;
    private String email;
    private String password;
    private String avatar;

    private List<Post> posts;

    public User(String userName, String email, String password, String avatar) {
        this.userId = idCounter++;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.posts = new ArrayList<>();
    }

    public User(String userName, String email, String password) {
        this.userId = idCounter++;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avatar = null;
        this.posts = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public boolean checkPassword(String password) { return this.password.equals(password); }

    public List<Post> getPosts() { return posts; }
    public void addPost(Post post) { this.posts.add(post); }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName='" + userName + '\'' + '}';
    }
}
