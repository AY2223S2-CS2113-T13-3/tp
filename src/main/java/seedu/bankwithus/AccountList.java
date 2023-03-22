package seedu.bankwithus;

import seedu.bankwithus.exceptions.InsufficientBalanceException;
import seedu.bankwithus.exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountList {
    private ArrayList<Account> accounts;
    private Ui ui;

    /**
     * Instantiates AccountList and creates a new account.
     * Called only when savefile is not found
     * 
     * @param bwu the main bankWithUs program
     */
    public AccountList(BankWithUs bwu) {
        accounts = new ArrayList<Account>();
        this.ui = bwu.getUi();
        createNewAccount();
    }

    /**
     * Instantiates AccountList and either:
     * 1. Load the saved information in the save file into
     *    the account list
     * 2. Create a brand new account if the save file was
     *    empty
     * 
     * @param scanner the scanner containing the information in the save file
     * @param bwu     the main bankWithUs program
     */
    public AccountList(Scanner scanner, BankWithUs bwu) {
        accounts = new ArrayList<Account>();
        this.ui = bwu.getUi();
        Parser parser = new Parser(this);
        try {
            parser.parseSavedFile(scanner);
        } catch (Exception e) {
            ui.showCorruptedSaveFileError();
            createNewAccount();
        }
    }

    /**
     * Returns the current account.
     * @return
     */
    public Account getCurrentAccount() {
        return accounts.get(0);
    }

    /**
     * Asks the user for the name and returns it in the form of
     * a string. Will keep looping so long as the user does not
     * give a valid name
     * 
     * @return the userName String
     */
    private String askUserForName() {
        ui.askForName();
        String userName = ui.getNextLine();
        userName.trim();
        if (userName.isBlank()) {
            ui.showBlankUserNameError();
            return askUserForName();
        }
        if (userName.contains(";")) {
            ui.showForbiddenCharacterError();
            return askUserForName();
        }
        return userName;
    }

    /**
     * Asks the user for their initial balance and returns it as a
     * float. Will keep looping so long as the user does not give
     * a valid balance.
     * 
     * @return balance in the form of a float
     */
    private float askUserForBalance() {
        ui.askForBalance();
        String balanceString = ui.getNextLine();
        balanceString.trim();
        try {
            float balance = Float.parseFloat(balanceString);
            if (balance < 0) {
                throw new NegativeAmountException();
            }
            return balance;
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
            return askUserForBalance();
        } catch (NegativeAmountException e) {
            ui.showNegativeAmountError();
            return askUserForBalance();
        }
    }

    /**
     * Creates a new account and adds it to the AccountList.
     *
     * @param name      Name of the new account to be added
     * @param balance   Balance of the new account to be added
     */
    public void addAccount(String name, float balance) {
        Account newAccount = new Account(name, balance);
        accounts.add(newAccount);
    }

    /**
     * Creates a new Account for a first time user
     */
    public void createNewAccount() {
        String userName = askUserForName();
        float balance = askUserForBalance();
        addAccount(userName, balance);
    }

    /**
     * Name and balance are separated by ; prepared to be saved
     *
     * @return returns all accounts details in String format
     */
    public String getAllAccountDetails() {
        String temp = "";
        for (Account acc : accounts) {
            temp += acc.getAccountName() + ";" + acc.getAccountBalance();
            temp += "\n";
        }
        return temp;
    }

    public void showBal() {
        float balance = getCurrentAccount().getAccountBalance();
        ui.showBal(balance);
    }

    public void depositMoney(String depositAmountString) throws NumberFormatException,
            NullPointerException, NegativeAmountException {
        float depositAmount = Float.parseFloat(depositAmountString);
        if (depositAmount < 0) {
            throw new NegativeAmountException();
        } else {
            getCurrentAccount().addBalance(depositAmount);
        }
    }

    public void withdrawMoney(String withdrawAmountString) throws NumberFormatException,
            NegativeAmountException, InsufficientBalanceException {
        float withdrawAmount = Float.parseFloat(withdrawAmountString);
        if (withdrawAmount < 0) {
            throw new NegativeAmountException();
        }
        float currentBalance = getCurrentAccount().getAccountBalance();
        if (currentBalance < withdrawAmount) {
            throw new InsufficientBalanceException();
        } else {
            getCurrentAccount().subtractBalance(withdrawAmount);
        }
    }
}
