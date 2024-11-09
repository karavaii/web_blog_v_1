import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicRepository {
    private List<Topic> topics = new ArrayList<>();

    // Метод для добавления новой темы
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    // Метод для получения всех тем
    public List<Topic> getAllTopics() {
        return new ArrayList<>(topics);
    }

    // Метод для поиска темы по ID
    public Optional<Topic> findById(int topicId) {
        return topics.stream()
                .filter(topic -> topic.getTopicId() == topicId)
                .findFirst();
    }
}
