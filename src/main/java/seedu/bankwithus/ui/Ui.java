package seedu.bankwithus.ui;

import seedu.bankwithus.user.Account;
import seedu.bankwithus.common.SaveGoal;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public void showSaveGoalCreated(String args, String untilWhenStr) {
        System.out.println("Try saving a minimum of $"+args+" until "+untilWhenStr);
        System.out.println("Save Goal has been created, Have fun staying frugal!");
    }
    public void showFileNotFoundError() {
        System.out.println("File not found, trying to create file.");
    }

    public void showFileCreated() {
        System.out.println("Savefile created successfully!");
    }

    public void showIOError() {
        System.out.println("Something's really wrong! Exiting program now.");
    }

    public void showNumberFormatError() {
        System.out.println("The input is not a valid number! Please try again.");
    }

    public void showNullInputError() {
        System.out.println("The input cannot be empty! Please try again.");
    }

    public void showCommandNotFoundError() {
        System.out.println("Not a valid command!");
    }

    public void showFarewellMessage() {
        System.out.println("Goodbye! Hope to see you again! :)");
    }

    public void showAddAccountMessage() {
        System.out.println("Account created!");
    }

    public void showDepositMessage() {
        System.out.println("New deposit added!");
    }

    public void showWithdrawMessage() {
        System.out.println("Withdrawal successful!");
    }

    /**
     * Creates a scanner in the Ui class
     *
     * @return
     */
    public void createScanner() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the next line of user input
     *
     * @return the next of user input
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        this.scanner.close();
    }

    public void printLine() {
        System.out.println("----------------------------");
    }

    public void viewAccount(String accDetails) {
        String[] accounts = accDetails.split("\\n");
        System.out.println("Current Account:");
        for (String account : accounts) {
            String name = account.split(";")[0];
            String bal = account.split(";")[1];
            System.out.println("Name: " + name);
            System.out.println("Balance: $" + bal);
            printLine();
        }
    }

    public void showBal(String finalBal) {
        System.out.println("You have $" + finalBal + " remaining!");
    }

    public void showNegativeAmountError() {
        System.out.println("Negative amount entered!");
    }

    public void showInsufficientBalanceMessage() {
        System.out.println("You do not have sufficient Balance");
        printLine();
    }

    public void showHelp() {
        printLine();
        System.out.println("help: displays the current menu");
        System.out.println("view-account: shows account name and balance");
        System.out.println("withdraw <amount>: withdraws <amount> from available balance");
        System.out.println("deposit <amount>: deposits <amount> and adds deposit to balance");
        System.out.println("save <amount> <date in dd-mm-yyyy format>: sets a saveGoal of <amount> until <date>");
        System.out.println("add-account: adds a new account");
        System.out.println("switch-to <account username>: switches to <account username> account");
        System.out.println("set-wl <amount>: sets <amount> to be the withdrawal limit");
        System.out.println("check-wl: shows the withdrawal limit and the amount of money withdrawn");
        System.out.println("          this month.");
        System.out.println("exit: quits program and saves");
        printLine();
    }

    public void greet() {
        System.out.println("Welcome to: ");
        System.out.println("\n" +
                " /$$$$$$$                      /$$       /$$      /$$" +
                " /$$   /$$     /$$       /$$   /$$          \n" +
                "| $$__  $$                    | $$      | $$  /$ | $$" +
                "|__/  | $$    | $$      | $$  | $$          \n" +
                "| $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$| $$ /$$$|" +
                " $$ /$$ /$$$$$$  | $$$$$$$ | $$  | $$  /$$$$$$$\n" +
                "| $$$$$$$  |____  $$| $$__  $$| $$  /$$/| $$/$$ $$" +
                " $$| $$|_  $$_/  | $$__  $$| $$  | $$ /$$_____/\n" +
                "| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ | $$$$_  " +
                "$$$$| $$  | $$    | $$  \\ $$| $$  | $$|  $$$$$$\n" +
                "| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ | $$$/ \\  " +
                "$$$| $$  | $$ /$$| $$  | $$| $$  | $$ \\____  $$\n" +
                "| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$| $$/   \\ " +
                " $$| $$  |  $$$$/| $$  | $$|  $$$$$$/ /$$$$$$$/\n" +
                "|_______/  \\_______/|__/  |__/|__/  \\__/|__/     " +
                "\\__/|__/   \\___/  |__/  |__/ \\______/ |_______/\n"
        );
    }

    public void askForName() {
        System.out.println("What's your name?");
    }

    public void showBlankUserNameError() {
        System.out.println("User Name cannot be blank.");
    }

    public void askForBalance() {
        System.out.println("How much would you like to add as Balance?");
    }

    public void showCorruptedSaveFileError() {
        System.out.println("Save file is corrupted!!! Creating new account...");
    }

    public void showForbiddenCharacterError() {
        System.out.println("Please do not put the character ';' in the name.");
    }
    //@@author Sherlock-YH
    public void showAccountDeleted(String name) {
        System.out.println("Account: " + name + " deleted");
        printLine();
    }
    //@@author Sherlock-YH
    public void showNoAccountFound() {
        System.out.println("Account is not found, please rectify the name");
        printLine();
    }
    //@@author Sherlock-YH
    public void showAccountNotFound() {
        System.out.println("There is no account");
        printLine();
    }

    public void showEmptyFile() {
        System.out.println("There is no saved account, please create a new account");
        printLine();
    }
    //@@author Sherlock-YH
    public void showNumberOfAccount(int accSize) {
        System.out.println("Found " + accSize + " Account");
        printLine();
    }
    //@@author Sherlock-YH
    public void showNewAccountAdded(Account acc) {
        System.out.println("Account created!");
        System.out.println("Name: " + acc.getAccountName());
        System.out.println("Balance: $" + acc.getAccountBalance());
        printLine();
    }
    //@@author Sherlock-YH
    public void showThereIsOnlyOneAccount() {
        System.out.println("There is only one account");
        printLine();
    }
    //@@author Sherlock-YH
    public void showMainAccountSwitched() {
        System.out.println("Current Account switched");
        printLine();
        System.out.println("Current Account is:");
        printLine();
    }

    public void showCurrentAccount(Account acc){
        System.out.println("Name: " + acc.getAccountName());
        System.out.println("Balance: $" + acc.getAccountBalance());
        printLine();
    }
    //@@author
    public void showWithdrawCancelled() {
        System.out.println("Withdrawal has been cancelled!");
    }

    public void showInsufficientArgsEntered() {
        System.out.println("missing one of the arguments relevant to the entered command, TRY AGAIN!");
    }

    public void failToMeetSaveGoal() {
        System.out.println("Withdrawing the requested amount would lead to failing to meet your personal Save goal");
        System.out.println("Are you sure you want to follow through? (Y/N)");
    }

    public void showGoal(SaveGoal goal) {
        System.out.println("Min amount to save: $" + Float.toString(goal.amtToSave));
        System.out.println("Deadline: "+goal.untilWhen);
        printLine();
    }

    public void showArgsWrongFormat() {
        System.out.println("please try again with a valid float and/or dd-mm-yy format");
    }

    public String getDeadline() {
        System.out.println("What would be the end date for the duration of your Saving goal?");
        return getNextLine();
    }
    
    //@@author tyuyang
    public void showWithdrawalLimitSet(String withdrawalLimit) {
        System.out.println("Withdrawal limit set to " + withdrawalLimit + "!");
    }

    public void showWithdrawalLimit(String withdrawalLimit) {
        if (withdrawalLimit != null) {
            System.out.println("Withdrawal limit is currently $" + withdrawalLimit + ".");
        } else {
            System.out.println("No withdrawal limit set!");
        }
    }

    public void showTotalAmountWithdrawn(String totalAmtWithdrawn) {
        System.out.println("You have withdrawn $" + totalAmtWithdrawn + " this month.");
    }

    public void showExceedsWithdrawalLimitError() {
        System.out.println("Apologies! Your transaction did not go through as it will result");
        System.out.println("in you exceeding your withdrawal limit!");
    }
    //@@author Sherlock-YH
    public void showDecimalPlacesError() {
        System.out.println("Please enter amount that only has two or less decimal places");
        printLine();
    }

    //@@author Sherlock-YH
    public void showInvalidInput() {
        System.out.println("Your input is invalid");
        printLine();
    }
}
