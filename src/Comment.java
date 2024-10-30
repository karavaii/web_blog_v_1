public class Comment {
    private static int idCounter = 1;

    private int commentId;
    private User user;
    private Post post;
    private String text;
    private String picture;

    public Comment(User user, Post post, String text, String picture) {
        this.commentId = idCounter++;
        this.user = user;
        this.post = post;
        this.text = text;
        this.picture = picture;
    }

    public int getCommentId() { return commentId; }
    public User getUser() { return user; }
    public Post getPost() { return post; }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", text='" + text + '\'' + '}';
    }
}