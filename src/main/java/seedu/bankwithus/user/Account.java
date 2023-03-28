package seedu.bankwithus.user;

import seedu.bankwithus.common.SaveGoal;
import seedu.bankwithus.common.WithdrawalChecker;
import seedu.bankwithus.exceptions.UserInputAmountNotValid;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Account {
    public SaveGoal saveGoal;
    private String name;
    private String balance;
    private WithdrawalChecker withdrawalChecker;
    //@@author Sherlock-YH
    /**
     * Instantiates an account object
     *
     * @param name    initialise in the name of the account
     * @param balance initialise the balance of the account
     */
    public Account(String name, String balance, String amtToSave, String untilWhen) {
        this.name = name;
        this.balance = balance;
        this.withdrawalChecker = new WithdrawalChecker();
        this.saveGoal = new SaveGoal(Float.parseFloat(amtToSave), untilWhen);
    }

    //@@author tyuyang
    public Account(String name, String balance, String totalAmtWithdrawn,
            LocalDate lastWithdrawnDate, String amtToSave, String untilWhen) {
        this.name = name;
        this.balance = balance;
        this.withdrawalChecker = new WithdrawalChecker(totalAmtWithdrawn, lastWithdrawnDate);
        this.saveGoal = new SaveGoal(Float.parseFloat(amtToSave), untilWhen);
    }
    //@@author Sherlock-YH
    public String getAccountName() {
        return name;
    }
    //@@author Sherlock-YH
    public String getAccountBalance() {
        return balance;
    }

    //@@author tyuyang
    public WithdrawalChecker getWithdrawalChecker() {
        return withdrawalChecker;
    }

    //@@author xiaoge26
    public void addBalance(float balanceToBeAdded) throws UserInputAmountNotValid {
        if (isMoreThanTwoDecimal(balanceToBeAdded)){
            throw new UserInputAmountNotValid();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(Float.parseFloat(balance) + balanceToBeAdded);
        this.balance = String.valueOf(formatted);
    }

    //@@author manushridiv
    public void subtractBalance(float currentBalance, float withdrawal) throws UserInputAmountNotValid {
        if (isMoreThanTwoDecimal(currentBalance)){
            throw new UserInputAmountNotValid();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(currentBalance - withdrawal);
        this.balance = String.valueOf(formatted);

        withdrawalChecker.updateTotalAmtWithdrawn(withdrawal);
    }

    //@@author vishnuvk47
    public void setSaveGoal(SaveGoal saveGoal, String args, String untilWhenStr) {

        this.saveGoal = saveGoal;
    }

    public String getName() {
        return this.name;
    }
    public SaveGoal getSaveGoal() {
        return this.saveGoal;
    }

    //@@author Sherlock-YH
    public boolean isMoreThanTwoDecimal(float num) {
        DecimalFormat df = new DecimalFormat("#.########");
        String formatted = df.format(num);
        int decimalIndex = formatted.indexOf('.');
        if (decimalIndex < 0) {
            return false;
        }
        int numDecimalPlaces = formatted.length() - decimalIndex - 1;
        return numDecimalPlaces > 2;
    }
}
