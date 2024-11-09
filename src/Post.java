public class Post {
    private static int idCounter = 1;

    private final int id;
    private int userId;
    private int topicId;
    private String title;
    private String text;
    private String picture;
    private String creationTime;
    private String modificationTime;

    // Новый конструктор, принимающий userId и topicId
    public Post(int userId, int topicId, String title, String text) {
        this.id = idCounter++;
        this.userId = userId;
        this.topicId = topicId;
        this.title = title;
        this.text = text;
        this.creationTime = getCurrentTime();
        this.modificationTime = null;
    }

    // Другой конструктор, включающий поле picture
    public Post(int userId, int topicId, String title, String text, String picture) {
        this(userId, topicId, title, text);
        this.picture = picture;
    }

    private String getCurrentTime() {
        // Возвращает текущее время в виде строки (например, с использованием java.time.LocalDateTime)
        return java.time.LocalDateTime.now().toString();
    }

    // Геттеры и сеттеры для полей

    public int getPostId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", picture='" + (picture != null ? picture : "нет") + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", modificationTime='" + (modificationTime != null ? modificationTime : "нет") + '\'' +
                '}';
    }
}
