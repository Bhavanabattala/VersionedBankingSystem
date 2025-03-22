import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

public class VersionedBankingSystem implements BankOperations {
    private double balance;
    private String username;
    private int currentVersion;

    private static class Node {
        LinkedList<String> transactions;
        double balanceAtVersion; // Stores the balance at this version
        Node prev, next;

        public Node(LinkedList<String> transactions, double balance) {
            this.transactions = new LinkedList<>(transactions);
            this.balanceAtVersion = balance;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head, tail, current;

    public VersionedBankingSystem(String username) {
        this.username = username;
        this.balance = 0;
        this.currentVersion = 0;
        LinkedList<String> initialTransactions = new LinkedList<>();
        head = tail = current = new Node(initialTransactions, 0);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            LinkedList<String> newTransactions = new LinkedList<>(current.transactions);
            newTransactions.add("Deposited: $" + amount);
            saveVersion(newTransactions);
            System.out.println(username + " deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            LinkedList<String> newTransactions = new LinkedList<>(current.transactions);
            newTransactions.add("Withdrew: $" + amount);
            saveVersion(newTransactions);
            System.out.println(username + " withdrew: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    @Override
    public void checkBalance() {
        System.out.println(username + ", your Current Balance: " + balance);
    }

    private void saveVersion(LinkedList<String> transactions) {
        Node newNode = new Node(transactions, balance); // Stores the current balance
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
        tail = current;
        currentVersion++;
    }

    // Method to View transaction history for a specific version 
    public void viewTransactionHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter version number to view history (0-" + currentVersion + "): ");
        int version = scanner.nextInt();

        if (version < 0 || version > currentVersion) {
            System.out.println("Invalid version number!");
            return;
        }

        Node temp = head;
        for (int i = 0; i < version; i++) {
            if (temp != null) {
                temp = temp.next;
            }
        }

        if (temp == null) {
            System.out.println("Invalid version number!");
            return;
        }

        System.out.println("\nTransaction History (Version " + version + "):");
        temp.transactions.forEach(transaction -> System.out.println(transaction));
        System.out.println("Balance at Version " + version + ": " + temp.balanceAtVersion);
    }

    //Method to Rollback to a specific version with version check
    public void rollbackToVersion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter version number to rollback (0-" + currentVersion + "): ");
        int version = scanner.nextInt();

        if (version < 0 || version > currentVersion) {
            System.out.println("Invalid version number! Cannot rollback.");
            return;
        }

        Node temp = head;
        for (int i = 0; i < version; i++) {
            if (temp != null) {
                temp = temp.next;
            }
        }

        if (temp == null) {
            System.out.println("Invalid version number! Cannot rollback.");
            return;
        }

        current = temp; // Set current pointer to the rollback version
        tail = current; // Update tail
        currentVersion = version;
        balance = current.balanceAtVersion; // Restore balance
        System.out.println("Rolled back to version " + version + " with balance: " + balance);
    }
}

// Bank class to manage multiple user accounts
class Bank {
    private ArrayList<VersionedBankingSystem> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Creating a new account
    public void createAccount(String username) {
        VersionedBankingSystem newUser = new VersionedBankingSystem(username);
        accounts.add(newUser);
        System.out.println(username + "Your Account created successfully " );
    }

    // Finding a user by username
    public VersionedBankingSystem getUser(String username) {
        for (VersionedBankingSystem user : accounts) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
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
            System.out.println("\nEnter your choice (1: Create Account, 2: Deposit, 3: Withdraw, 4: Check Balance, 5: View History, 6: Rollback, 7: Exit):");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                // Creating a new account
                System.out.println("Enter your name to create an account:");
                String name = scanner.nextLine();
                bank.createAccount(name);
                continue;
            }

            // Asking for username before every operation except for account creation
            System.out.println("Enter your username:");
            String enteredUsername = scanner.nextLine();

            // Validating username
            VersionedBankingSystem currentUser = bank.getUser(enteredUsername);
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
                    currentUser.viewTransactionHistory();
                    break;
                case 6:
                    currentUser.rollbackToVersion();
                    break;
                case 7:
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
