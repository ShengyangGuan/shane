import java.util.Scanner;

public class UserInterface {
    private FileUserAuthentication userAuthentication;

    public UserInterface(FileUserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public User registerOrLoginUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    return registerUser();
                case 2:
                    return loginUser();
                case 3:
                    System.out.println("Exiting...");
                    return null;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private User registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userAuthentication.register(username, password)) {
            System.out.println("Registration successful.");
            return new User(username, password);
        } else {
            System.out.println("Registration failed. Username may already be taken.");
            return null;
        }
    }

    private User loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userAuthentication.authenticate(username, password)) {
            System.out.println("Login successful.");
            return new User(username, password);
        } else {
            System.out.println("Login failed. Invalid username or password.");
            return null;
        }
    }
}
