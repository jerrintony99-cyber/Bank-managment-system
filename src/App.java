import java.util.*;

public class App {
    static class Account {
        private String acc_id;
        private String acc_pin;
        private double balance;

        Account(String acc_id, String acc_pin) {
            this.acc_id = acc_id;
            this.acc_pin = acc_pin;
            this.balance = 0;
        }

        public boolean login(String id, String pin) {
            if (id.equals(acc_id) && pin.equals(acc_pin)) {
                System.out.println("Login successful");
                return true;
            }
            return false;
        }

        public void Deposit(double amt) {
            if (amt <= 0) {
                System.out.println("Invalid amount. Cannot perform operation!");
                return;
            }
            balance += amt;
            System.out.println("Amount " + amt + " has been added to your account");
        }

        public void Withdraw(double amt) {
            if (amt <= 0) {
                System.out.println("Invalid amount.");
                return;
            }
            if (amt > balance) {
                System.out.println("Insufficient balance.");
                return;
            }
            balance -= amt;
            System.out.println("Amount deducted from balance.");
            System.out.println("Current balance: " + balance);
        }

        public void displayBalance() {
            System.out.println("Current balance: " + balance);
        }

        public String getAccId() {
            return acc_id;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        Account loggedInAccount = null;

        while (true) {
            System.out.println("\n------Bank Management System------");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose your operation: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("----Bank Login System----");
                    System.out.print("Enter login ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pin = sc.nextLine();
                    boolean found = false;
                    for (Account account : accounts) {
                        if (account.login(id, pin)) {
                            loggedInAccount = account;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid credentials");
                    }
                    break;
                case 2:
                    System.out.print("Enter your new username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String password = sc.nextLine();
                    Account newAccount = new Account(username, password);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully!");
                    break;
                case 3:
                    if (loggedInAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    loggedInAccount.Deposit(amount);
                    break;
                case 4:
                    if (loggedInAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    loggedInAccount.Withdraw(amt);
                    break;
                case 5:
                    if (loggedInAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    loggedInAccount.displayBalance();
                    break;
                case 6:
                    if (loggedInAccount == null) {
                        System.out.println("No account is currently logged in.");
                    } else {
                        loggedInAccount = null;
                        System.out.println("Logged out successfully.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}