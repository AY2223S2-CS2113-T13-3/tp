package seedu.bankwithus.user;

import seedu.bankwithus.BankWithUs;
import seedu.bankwithus.common.SaveGoal;
import seedu.bankwithus.common.WithdrawalChecker;
import seedu.bankwithus.exceptions.AccountNotFoundException;
import seedu.bankwithus.exceptions.CorruptedSaveFileException;
import seedu.bankwithus.exceptions.ExceedsWithdrawalLimitException;
import seedu.bankwithus.exceptions.InsufficientBalanceException;
import seedu.bankwithus.exceptions.NegativeAmountException;
import seedu.bankwithus.exceptions.NoAccountException;
import seedu.bankwithus.exceptions.SaveFileIsEmptyException;
import seedu.bankwithus.parser.Parser;
import seedu.bankwithus.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
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
     * Instantiates AccountList for unit testing
     */
    public AccountList() {
        accounts = new ArrayList<Account>();
        this.ui = new Ui();
    }

    /**
     * Instantiates AccountList and either:
     * 1. Load the saved information in the save file into
     * the account list
     * 2. Create a brand new account if the save file was
     * empty
     *
     * @param scanner the scanner that reads the save file
     * @param bwu     the main bankWithUs program
     */
    public AccountList(Scanner scanner, BankWithUs bwu) {
        accounts = new ArrayList<>();
        this.ui = bwu.getUi();
        Parser parser = new Parser(this);
        try {
            parser.parseSavedFile(scanner);
        } catch (CorruptedSaveFileException e) {
            ui.showCorruptedSaveFileError();
            createNewAccount();
        } catch (SaveFileIsEmptyException e) {
            ui.showEmptyFile();
            createNewAccount();
        }
    }

    //@@author xiaoge26
    /**
     * Returns the current account.
     *
     * @return
     */
    public Account getMainAccount() {
        return accounts.get(0);
    }

    //@@author
    /**
     * Asks the user for the name and returns it in the form of
     * S string. Will keep looping so long as the user does not
     * Give a valid name
     *
     * @return the userName String
     */
    public String askUserForName() {
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
     * Float. Will keep looping so long as the user does not give
     * A valid balance.
     *
     * @return balance in the form of a float
     */
    public String askUserForBalance() {
        ui.askForBalance();
        String balanceString = ui.getNextLine();
        balanceString = balanceString.trim();
        balanceString = balanceString.replaceFirst("^0+(?!$)", "");
        try {
            float balance = Float.parseFloat(balanceString);
            if (balance < 1) {
                balanceString = "0" + balanceString;
            }
            if (balance < 0) {
                throw new NegativeAmountException();
            }
            return balanceString;
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
            return askUserForBalance();
        } catch (NegativeAmountException e) {
            ui.showNegativeAmountError();
            return askUserForBalance();
        }
    }

    //@@author xiaoge26
    /**
     * Creates a new account and adds it to the AccountList.
     *
     * @param name            Name of the new account to be added
     * @param balance         Balance of the new account to be added
     * @param withdrawalLimit Withdrawal limit set by the user, blank if not set
     */
    public void addAccount(String name, String balance, String withdrawalLimit) {
        Account newAccount = new Account(name, balance, "0", "01-01-2001");
        if (!withdrawalLimit.isBlank()) {
            Float withdrawalLimitFloat = Float.parseFloat(withdrawalLimit);
            newAccount.getWithdrawalChecker().setWithdrawalLimit(withdrawalLimitFloat);
        }
        accounts.add(newAccount);
        ui.showNewAccountAdded(newAccount);
    }

    /**
     * Simple method that adds an account
     * @param name - Name of the account
     * @param balance -The available balance
     * @param withdrawalLimit - withdrawal limit to be set
     * @param amtToSave - save Goal amount
     * @param untilWhen - deadline for save goal amount
     */
    public void addAccount(String name, String balance, String withdrawalLimit, String amtToSave, String untilWhen) {
        Account newAccount = new Account(name, balance, amtToSave, untilWhen);
        if (!withdrawalLimit.isBlank()) {
            Float withdrawalLimitFloat = Float.parseFloat(withdrawalLimit);
            newAccount.getWithdrawalChecker().setWithdrawalLimit(withdrawalLimitFloat);
        }
        accounts.add(newAccount);
        ui.showNewAccountAdded(newAccount);
    }

    //@@author tyuyang
    /**
     * Creates a new account with withdrawal info and adds it to the AccountList
     * 
     * @param name              Name of the new account to be added
     * @param balance           Balance of the new account to be added
     * @param totalAmtWithdrawn Total amount withdrawn from the account this month
     * @param lastWithdrawnDate Date of the last withdrawal from the account
     * @param withdrawalLimit   Withdrawal limit set by the user, blank if not set
     */
    public void addAccount(String name, String balance, String totalAmtWithdrawn,
            LocalDate lastWithdrawnDate, String withdrawalLimit, String amtToSave, String untilWhen) {
        Account newAccount = new Account(name, balance, totalAmtWithdrawn, lastWithdrawnDate, amtToSave, untilWhen);
        if (!withdrawalLimit.isBlank()) {
            Float withdrawalLimitFloat = Float.parseFloat(withdrawalLimit);
            newAccount.getWithdrawalChecker().setWithdrawalLimit(withdrawalLimitFloat);
        }
        accounts.add(newAccount);
        ui.showNewAccountAdded(newAccount);
    }
    
    //@@author vishnuvk47
    /**
     * Creates a new Account for a first time user
     */
    public void createNewAccount() {
        String userName = askUserForName();
        String balance = askUserForBalance();
        addAccount(userName, balance, "");
    }

    //@@author Sherlock-YH
    /**
     * Name and balance are separated by ; prepared to be saved
     *
     * @return returns all accounts details in String format
     */
    public String getAllAccountDetails() throws AccountNotFoundException {
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException();
        } else {
            StringBuilder temp = new StringBuilder();
            for (Account acc : accounts) {
                temp.append(acc.getAccountName()).append(";").append(acc.getAccountBalance());
                //saving withdrawal information
                temp.append(";").append(acc.getWithdrawalChecker().toString());
                //save Save Goal info
                SaveGoal savings = acc.getSaveGoal();
                temp.append(";").append(savings.amtToSave);
                temp.append(";").append(savings.untilWhen);
                temp.append("\n");
            }
            return temp.toString();
        }
    }

    /**
     * Prints to the UI the available balance of the current user
     */
    //@@author
    public void showBal() {
        String balance = getMainAccount().getAccountBalance();
        float bal = Float.parseFloat(balance);
        ui.showBal(balance);

    }

    /**
     * Deposits a user specified amount to the current account's balance
     * @param depositAmountString - amount to be deposited
     * @throws NumberFormatException
     * @throws NullPointerException
     * @throws NegativeAmountException
     */
    //@@author xiaoge26
    public void depositMoney(String depositAmountString) throws NumberFormatException,
            NullPointerException, NegativeAmountException {
        float depositAmount = Float.parseFloat(depositAmountString);
        if (depositAmount < 0) {
            throw new NegativeAmountException();
        } else {
            getMainAccount().addBalance(depositAmount);
        }
    }

    //@@author vishnuvk47
    /**
     * Formats the date into the dd-MM-yyyy format
     * @param date
     * @return teh date in the dd-MM-yyyy format
     */
    public LocalDate handleDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(formattedDate, format);
    }

    /**
     * Withdraws a user specified amount from the current account
     * Also checks if user meets their withdrawal limit and save goal requirement
     * @param withdrawAmountString - amount to be withdrawn
     * @throws NumberFormatException
     * @throws NegativeAmountException
     * @throws InsufficientBalanceException
     * @throws ExceedsWithdrawalLimitException
     */
    //@@author manushridiv
    public void withdrawMoney(String withdrawAmountString) throws NumberFormatException,
            NegativeAmountException, InsufficientBalanceException, ExceedsWithdrawalLimitException {
        float withdrawAmount = Float.parseFloat(withdrawAmountString);
        if (withdrawAmount < 0) {
            throw new NegativeAmountException();
        }
        float currentBalance = Float.parseFloat(getMainAccount().getAccountBalance());
        if (currentBalance < withdrawAmount) {
            throw new InsufficientBalanceException();
        } else if (getMainAccount().getWithdrawalChecker().willExceedWithdrawalLimit(withdrawAmount)) {
            throw new ExceedsWithdrawalLimitException();
        } else if(willFailsSaveGoal(currentBalance, withdrawAmount)) {
            ui.failToMeetSaveGoal();
            handleProceed(withdrawAmount, currentBalance);
        } else {
            getMainAccount( ).subtractBalance(currentBalance,withdrawAmount);
            ui.showWithdrawMessage();
        }
    }

    /**
     * Finds the respective account to be deleted at users request
     * Forces users to create a new account if no account remains after deletion executes
     * @param name - name of the account to delet
     * @param acc - the account to be checked if it matches the account name to be deleted
     */
    public void findAccountToDelete(String name, Account acc) {
        if (acc.getAccountName().contains(name)) {
            accounts.remove(acc);
            ui.showAccountDeleted(name);
            if(accounts.size() < 1) {
                createNewAccount();
            }
        }
    }

    /**
     * Baseline method for the delete account command
     * @param name - name of the account to be deleted
     */
    //@@author Sherlock-YH
    public void deleteAccount(String name) {
        for (Account acc : accounts) {
            findAccountToDelete(name, acc);
            return;
        }
        ui.showNoAccountFound();
    }

    //@@author Sherlock-YH
    public int getSize() {
        return accounts.size();
    }

    /**
     * Method that allows that handles switching between users
     * @param accName - account to be switched into
     * @throws NoAccountException
     */
    //@@author Sherlock-YH
    public void switchMainAccount(String accName) throws NoAccountException {
        //swap acc to the head of AccountList
        if (accounts.size() == 0) {
            throw new NoAccountException();
        } else if (accounts.size() == 1) {
            ui.showThereIsOnlyOneAccount();
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccountName().contains(accName)) {
                    Collections.swap(accounts, i, 0);
                    ui.showMainAccountSwitched();
                    ui.showCurrentAccount(accounts.get(0));
                    return;
                }
            }
            ui.showNoAccountFound();
        }
    }

    //@@author tyuyang
    /**
     * Sets the withdrawal limit of the main account. Modifies the attribute
     * WithdrawalLimit in the WithdrawalChecker class directly
     * 
     * @param args the user input
     * 
     * @throws NegativeAmountException if input is negative
     */
    public void setWithdrawalLimit(String args) throws NegativeAmountException {
        float withdrawalLimit;
        try {
            withdrawalLimit = Float.parseFloat(args);
        } catch (Exception e) {
            throw new NumberFormatException();
        }
        if (withdrawalLimit < 0) {
            throw new NegativeAmountException();
        }
        getMainAccount().getWithdrawalChecker().setWithdrawalLimit(withdrawalLimit);
    }
    
    //@@author Sherlock-YH
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    //@@author vishnuvk47
    /**
     * Handles overwriting of saveGoal at users own discretion
     * @param withdrawAmount
     * @param currentBalance
     */
    public void handleProceed(float withdrawAmount, float currentBalance) {
        String yesOrNo = ui.getNextLine();
        while(!(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("n"))) {
            System.out.println("Please enter ONLY either Y for Yes and N for No.");
            yesOrNo = ui.getNextLine();
        }
        if(yesOrNo.equalsIgnoreCase("y")) {
            getMainAccount( ).subtractBalance(currentBalance,withdrawAmount);
            getMainAccount().saveGoal.amtToSave = 0;
            ui.showWithdrawMessage();

        } else {
            ui.showWithdrawCancelled();
        }
    }

    /**
     * Primary function that handles the setting and exception handling when saveGoal is called
     * @param args
     * @param untilWhenStr
     */
    public void handleSaveGoal(String args, String untilWhenStr) {
        try {
            float toSave = Float.parseFloat(args);
            if (toSave < 0) {
                ui.showNegativeAmountError();
            } else if (isDateFormatValid(untilWhenStr)) {
                SaveGoal saveGoal = new SaveGoal(toSave, untilWhenStr);
                getMainAccount().setSaveGoal(saveGoal, args, untilWhenStr);
                ui.showSaveGoalCreated(args, untilWhenStr);
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Checks if the date is entered in teh valid DD-MM-YYYY format
     * @param date
     * @return True if valid format and False if invalid format
     */
    public boolean isDateFormatValid(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeException e) {
            System.out.println("Incorrect Date format, Try again following dd-MM-YYYY format!");
            return false;
        }

    }

    /**
     * Prints to UI the save goal that the user has set for himself
     */
    public void showGoal() {
        SaveGoal goal = getMainAccount().getSaveGoal();
        if(goal.amtToSave <= 0) {
            System.out.println("you do not have any Save Goal");
        } else {
            ui.showGoal(goal);
        }
    }

    /**
     * Checks to see if the amount being withdrawn exceeds save Goal requirements
     * @param currentBalance
     * @param withdrawAmount
     * @return True if fails to meet save Goal and False if meets save Goal requirements
     */
    public Boolean willFailsSaveGoal(float currentBalance, float withdrawAmount) {
        float expectedBal = currentBalance - withdrawAmount;
        LocalDate tdy = LocalDate.now();
        LocalDate tdyDate = handleDate(tdy);
        boolean exceedsSaveGoal = getMainAccount().getSaveGoal().amtToSave > expectedBal;
        boolean deadlineNotPassed = getMainAccount().getSaveGoal().untilWhen.isAfter(tdyDate);
        return (exceedsSaveGoal && deadlineNotPassed);
    }

    //@@author tyuyang
    public String[] checkWithdrawalLimit() {
        String[] wlInfo = new String[2];
        WithdrawalChecker withdrawalChecker = this.getMainAccount().getWithdrawalChecker();
        withdrawalChecker.updateTotalAmtWithdrawn(0);
        wlInfo[0] = withdrawalChecker.getWithdrawalLimit();
        wlInfo[1] = withdrawalChecker.getTotalAmtWithdrawn();
        return wlInfo;
    }
}
