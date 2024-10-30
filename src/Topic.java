public class Topic {
    private static int idCounter = 1;

    private int topicId;
    private String topicName;
    private String description;

    public Topic(String topicName, String description) {
        this.topicId = idCounter++;
        this.topicName = topicName;
        this.description = description;
    }

    public int getTopicId() { return topicId; }
    public String getTopicName() { return topicName; }

    @Override
    public String toString() {
        return "Topic{" + "topicId=" + topicId + ", topicName='" + topicName + '\'' + '}';
    }
}
