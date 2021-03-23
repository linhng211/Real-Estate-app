import java.util.ArrayList;
import java.util.Scanner;

public class Accounts {
    private static Accounts accounts;
    private static ArrayList<Account> accountList = new ArrayList<Account>();

    private Accounts() {
        accountList = DataLoader.loadAccounts();
    }

    public static Accounts getInstance() {
        if (accounts == null) {
            accounts = new Accounts();
        }
        return accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accountList;
    }

    public static void addAccount(String email, Account newAccount) {
        boolean alreadyExists = false;
        for(Account account : accountList) {
            if(account.getEmail().equals(email)) {
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists) {
            accountList.add(newAccount);
            DataWriter.saveAccounts();
        }
    }


    public Account searchAccountID(int id) {
        for(Account account : accountList) {
            if(account.getAccountID() == id) return account;
        }
        return null;
    }

    public Account searchAccountUsername(String username) {
        for(Account account : accountList) {
            if(account.getUsername().equals(username)) return account;
        }
        System.out.println("No user matched your search.");
        return null;
    }

    public static Account login(String username, String password) {
        for(Account account : accountList) {
            if(account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Welcome back, " + account.getFirstName() + ' ' + account.getLastName());
                return account;
            }
        }
        System.out.println("Invalid Credentials.");
        return null;
    }

    public void removeAccount(int accountID) {
        accountList.removeIf(account -> account.getAccountID() == accountID);
    }
}