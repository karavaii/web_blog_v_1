import java.util.Optional;
import java.util.Scanner;

public class Application {

    private static final UserRepository userRepository = new UserRepository();
    private static final PostRepository postRepository = new PostRepository();
    private static final TopicRepository topicRepository = new TopicRepository();
    private static final CommentRepository commentRepository = new CommentRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Добавление тестовых тем и постов
        seedData();

        while (true) {
            System.out.println("1. Создать нового пользователя");
            System.out.println("2. Войти как пользователь");
            System.out.println("3. Показать все темы");
            System.out.println("4. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser(scanner);
                case 2 -> login(scanner);
                case 3 -> showAllTopics();
                case 4 -> {
                    System.out.println("Выход из приложения...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        /* System.out.print("Введите URL фото: ");
        String avatar = scanner.nextLine(); */

        User newUser = new User(username, email, password);
        userRepository.addUser(newUser);
        System.out.println("Пользователь успешно создан: " + newUser);
    }


    private static void login(Scanner scanner) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (userRepository.authenticate(username, password)) {
            Optional<User> user = userRepository.findByUsername(username);
            System.out.println("Вход выполнен. Добро пожаловать, " + username + "!");
            user.ifPresent(Application::userMenu);
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    private static void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Добавить пост");
            System.out.println("2. Просмотреть посты по теме");
            System.out.println("3. Добавить комментарий к посту");
            System.out.println("4. Выйти из аккаунта");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createPost(scanner, user);
                case 2 -> viewPostsByTopic(scanner);
                case 3 -> addCommentToPost(scanner, user);
                case 4 -> {
                    System.out.println("Выход из аккаунта...");
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void createPost(Scanner scanner, User user) {
        System.out.print("Введите ID темы: ");
        int topicId = scanner.nextInt();
        scanner.nextLine();
        Optional<Topic> topic = topicRepository.findById(topicId);

        if (topic.isPresent()) {
            System.out.print("Введите заголовок поста: ");
            String title = scanner.nextLine();
            System.out.print("Введите содержание поста: ");
            String text = scanner.nextLine();

            Post post = new Post(user.getUserId(), topicId, title, text);
            postRepository.addPost(post);
            System.out.println("Пост успешно добавлен: " + post);
        } else {
            System.out.println("Тема с таким ID не найдена.");
        }
    }

    private static void viewPostsByTopic(Scanner scanner) {
        System.out.print("Введите ID темы: ");
        int topicId = scanner.nextInt();
        scanner.nextLine(); // Очищаем новую строку
        Optional<Topic> topic = topicRepository.findById(topicId);

        if (topic.isPresent()) {
            System.out.println("Посты в теме '" + topic.get().getTopicName() + "':");
            postRepository.findByTopicId(topicId).forEach(post ->
                    System.out.println("ID: " + post.getPostId() + ", Заголовок: " + post.getTitle() + ", Текст: " + post.getText()));
        } else {
            System.out.println("Тема с таким ID не найдена.");
        }
    }

    private static void addCommentToPost(Scanner scanner, User user) {
        System.out.print("Введите ID поста: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Очищаем новую строку

        Optional<Post> post = postRepository.getAllPosts().stream().filter(p -> p.getPostId() == postId).findFirst();

        if (post.isPresent()) {
            System.out.print("Введите текст комментария: ");
            String text = scanner.nextLine();

            Comment comment = new Comment(user, post.get(), text, null);
            commentRepository.addComment(comment);
            System.out.println("Комментарий успешно добавлен: " + comment);
        } else {
            System.out.println("Пост с таким ID не найден.");
        }
    }

    private static void showAllTopics() {
        System.out.println("Список всех тем:");
        topicRepository.getAllTopics().forEach(topic ->
                System.out.println("ID: " + topic.getTopicId() + ", Название: " + topic.getTopicName() + ", Описание: " + topic.getDescription()));
    }

    private static void seedData() {
        // Создаем тестовые темы
        Topic techTopic = new Topic("Технологии", "Все о технологиях");
        Topic scienceTopic = new Topic("Наука", "Все о науке");
        topicRepository.addTopic(techTopic);
        topicRepository.addTopic(scienceTopic);

        // Создаем тестовые посты
        User testUser = new User("testUser", "example@gmail.com", "password");
        userRepository.addUser(testUser);
        postRepository.addPost(new Post(testUser.getUserId(), techTopic.getTopicId(), "Пост о Java", "Описание"));
        postRepository.addPost(new Post(testUser.getUserId(), scienceTopic.getTopicId(), "Пост о физике", "Описание"));
    }
}
