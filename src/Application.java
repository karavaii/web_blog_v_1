import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private List<User> users = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();
    private User currentUser;

    public Application() {
        // Создаем несколько тем для примера
        topics.add(new Topic("Technology", "All about tech"));
        topics.add(new Topic("Health", "Health discussions"));
        topics.add(new Topic("Education", "Education topics"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Console Application!");

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (choice == 1) {
                    register(scanner);
                } else if (choice == 2) {
                    login(scanner);
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("1. Add Post");
                System.out.println("2. View Posts by Topic");
                System.out.println("3. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    addPost(scanner);
                } else if (choice == 2) {
                    viewPostsByTopic(scanner);
                } else if (choice == 3) {
                    currentUser = null;
                }
            }
        }
    }

    private void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, email, password, null);
        users.add(newUser);
        System.out.println("User registered successfully!");
    }

    private void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUserName().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                System.out.println("Logged in successfully!");
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private void addPost(Scanner scanner) {
        System.out.println("Choose topic:");
        for (Topic topic : topics) {
            System.out.println(topic.getTopicId() + ". " + topic.getTopicName());
        }
        int topicId = scanner.nextInt();
        scanner.nextLine();

        Topic selectedTopic = topics.stream().filter(t -> t.getTopicId() == topicId).findFirst().orElse(null);
        if (selectedTopic == null) {
            System.out.println("Invalid topic.");
            return;
        }

        System.out.print("Enter post text: ");
        String text = scanner.nextLine();

        Post newPost = new Post(selectedTopic, currentUser, text, null);
        currentUser.addPost(newPost);
        System.out.println("Post added successfully!");
    }

    private void viewPostsByTopic(Scanner scanner) {
        System.out.println("Choose topic to view posts:");
        for (Topic topic : topics) {
            System.out.println(topic.getTopicId() + ". " + topic.getTopicName());
        }
        int topicId = scanner.nextInt();

        Topic selectedTopic = topics.stream().filter(t -> t.getTopicId() == topicId).findFirst().orElse(null);
        if (selectedTopic == null) {
            System.out.println("Invalid topic.");
            return;
        }

        for (User user : users) {
            for (Post post : user.getPosts()) {
                if (post.getTopic().equals(selectedTopic)) {
                    System.out.println(post);
                }
            }
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}
