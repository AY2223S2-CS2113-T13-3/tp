package seedu.bankwithus.user;

import seedu.bankwithus.common.SaveGoal;
import seedu.bankwithus.common.WithdrawalChecker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Account {
    public SaveGoal saveGoal;
    private String name;
    private BigDecimal balance;
    private WithdrawalChecker withdrawalChecker;
    //@@author Sherlock-YH
    /**
     * Instantiates an account object.
     *
     * @param name    initialise in the name of the account
     * @param balance initialise the balance of the account
     */
    public Account(String name, String balance, String amtToSave, LocalDate untilWhen) {
        this.name = name;
        this.balance = new BigDecimal(balance).setScale(2, RoundingMode.CEILING);
        this.withdrawalChecker = new WithdrawalChecker();
        this.saveGoal = new SaveGoal(new BigDecimal(amtToSave), untilWhen);
    }

    //@@author tyuyang
    public Account(String name, String balance, String totalAmtWithdrawn,
            LocalDate lastWithdrawnDate, String amtToSave, LocalDate untilWhen) {
        this.name = name;
        this.balance = new BigDecimal(balance).setScale(2, RoundingMode.CEILING);;
        this.withdrawalChecker = new WithdrawalChecker(totalAmtWithdrawn, lastWithdrawnDate);
        this.saveGoal = new SaveGoal(new BigDecimal(amtToSave), untilWhen);
    }
    //@@author Sherlock-YH
    public String getAccountName() {
        return name;
    }
    //@@author Sherlock-YH
    public BigDecimal getAccountBalance() {
        return balance;
    }

    //@@author tyuyang
    public WithdrawalChecker getWithdrawalChecker() {
        return withdrawalChecker;
    }

    //@@author xiaoge26
    public void addBalance(BigDecimal balanceToBeAdded) {
        this.balance = this.balance.add(balanceToBeAdded).setScale(2, RoundingMode.CEILING);
    }

    //@@author manushridiv
    public void subtractBalance(BigDecimal currentBalance, BigDecimal withdrawal) {
        this.balance = currentBalance.subtract(withdrawal);
        withdrawalChecker.updateTotalAmtWithdrawn(withdrawal); //check later
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
}
