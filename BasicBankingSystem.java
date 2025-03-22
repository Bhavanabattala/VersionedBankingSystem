import java.util.ArrayList;
import java.util.Scanner;

interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

public class BasicBankingSystem implements BankOperations {
    private double balance;
    private String username;

    public BasicBankingSystem(String username) {
        this.username = username;
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    // method to deposit
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(username + "Successfully deposited: " + amount);
        } else {
            System.out.println(" Deposit amount must be positive.");
        }
    }

    // method to withdraw 
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(username + " Successfully withdrew: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }

    // method to check balance 
    @Override
    public void checkBalance() {
        System.out.println(username + ", your Current Balance is : " + balance);
    }
}

// Bank class to manage multiple user accounts
class Bank {
    private ArrayList<BasicBankingSystem> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Creating a new account
    public void createAccount(String username) {
        BasicBankingSystem newUser = new BasicBankingSystem(username);
        accounts.add(newUser);
        System.out.println( username + " Your account is created successfully " );
    }

    // Finding a user by username
    public BasicBankingSystem getUser(String username) {
        for (BasicBankingSystem user : accounts) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;//returning null if user is not found
    }
}

// Main class 
class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        boolean exit = false;

        while (!exit) {
            // Displaying the menu to the user
            System.out.println("\nEnter your choice (1: Create Account, 2: Deposit, 3: Withdraw, 4: Check Balance, 5: Exit):");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                // Creating a new account
                System.out.println("Please Enter your name to create an account:");
                String name = scanner.nextLine();
                bank.createAccount(name);
                continue;
            }

            // Asking for username before every operation except for account creation
            System.out.println("Enter your username:");
            String enteredUsername = scanner.nextLine();

            // Validating username
            BasicBankingSystem currentUser = bank.getUser(enteredUsername);
            if (currentUser == null) {
                System.out.println("Invalid username! Please enter the correct username.");
                continue;
            }

            switch (choice) {
                case 2:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Enter withdraw amount:");

                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount);
                    break;
                case 4:
                    currentUser.checkBalance();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        scanner.close();
    }
}
