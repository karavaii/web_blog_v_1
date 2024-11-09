import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    // Метод для добавления пользователя
    public void addUser(User user) {
        users.add(user);
    }

    // Метод для поиска пользователя по имени
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst();
    }

    // Метод для проверки аутентификации пользователя
    public boolean authenticate(String username, String password) {
        return findByUsername(username)
                .map(user -> user.checkPassword(password))
                .orElse(false);
    }

    // Метод для получения всех пользователей
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
