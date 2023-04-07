package seedu.bankwithus;

import org.junit.jupiter.api.Test;
import seedu.bankwithus.exceptions.MoreThanTwoDecimalPlace;
import seedu.bankwithus.user.AccountList;
import seedu.bankwithus.exceptions.NegativeAmountException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountListTest {
    @Test
    void depositMoney_notANumber_expectException() {
        String amountString = "abc";
        String name = "Bob";
        String balance = "0";
        AccountList accountList = new AccountList();
        accountList.addAccount(name, balance, "");
        assertThrows(NumberFormatException.class,
                () -> accountList.depositMoney(amountString));
    }

    @Test
    void depositMoney_negativeNumber_expectException() {
        String amountString = "-1000";
        String name = "Bob";
        String balance = "100";
        AccountList accountList = new AccountList();
        accountList.addAccount(name, balance, "");
        assertThrows(NegativeAmountException.class,
                () -> accountList.depositMoney(amountString));
    }

    //@@author Sherlock-YH
    @Test
    void depositMoney_moreThanTwoDecimalPlaces_expectException() {
        String amountString = "1000.111";
        String name = "SHhhh";
        String balance = "1234";
        AccountList accountList = new AccountList();
        accountList.addAccount(name, balance, "");
        assertThrows(MoreThanTwoDecimalPlace.class,
                () -> accountList.depositMoney(amountString));

    }

    //@@author Sherlock-YH
    @Test
    void withdrawMoney_moreThanTwoDecimalPlaces_expectException() {
        String amountString = "0.111";
        String name = "SHhhh";
        String balance = "12333";
        AccountList accountList = new AccountList();
        accountList.addAccount(name, balance, "");
        assertThrows(MoreThanTwoDecimalPlace.class,
                () -> accountList.withdrawMoney(amountString));

    }

    //@@author vishnuvk47
    @Test
    void withdrawNegativeAmount() {
        AccountList accountList = new AccountList();
        accountList.addAccount("James", "1000", "");
        assertThrows(NegativeAmountException.class, () -> {
            accountList.withdrawMoney("-100");
        });
    }

    @Test
    void withdrawNegativeAmountWithDecimal() {
        AccountList accountList = new AccountList();
        accountList.addAccount("James", "1000", "");
        assertThrows(NegativeAmountException.class, () -> {
            accountList.withdrawMoney("-21.67");
        });
    }

    //@@author xiaoge26
    @Test
    void testingAddAccount_validInput_expectNewAccount() {
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount("Jane", "1000", "");
        assertEquals("Jane", testAccountList.getMainAccount().getName());
        assertEquals(new BigDecimal("1000.00"), testAccountList.getMainAccount().getAccountBalance());
    }

    //@@author tyuyang
    @Test
    void setWithdrawalLimit_validInput_noException() {
        AccountList testAccountList = new AccountList();
        //No initial withdrawal limit
        testAccountList.addAccount("Hihi", "1000", "");
        assertDoesNotThrow(() -> testAccountList.setWithdrawalLimit("1.11"));
        assertEquals("1.11", testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());
        
        //With initial withdrawal limit
        assertDoesNotThrow(() -> testAccountList.setWithdrawalLimit("2.23"));
        assertEquals("2.23", testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());
    }

    //@@author tyuyang
    @Test
    void setWithdrawalLimit_negativeInput_negativeAmountExceptionThrown() {
        AccountList testAccountList = new AccountList();
        //No initial withdrawal limit
        testAccountList.addAccount("Hihi", "1000", "");
        assertThrows(NegativeAmountException.class, 
                () -> testAccountList.setWithdrawalLimit("-100"));
        assertEquals(null, testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());

        //With initial withdrawal limit
        assertDoesNotThrow(() -> testAccountList.setWithdrawalLimit("1000"));
        assertThrows(NegativeAmountException.class, 
                () -> testAccountList.setWithdrawalLimit("-1.10"));
        assertEquals("1000", testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());
    }

    //@@author tyuyang
    @Test
    void setWithdrawalLimit_nonNumberInput_numberFormatExceptionThrown() {
        AccountList testAccountList = new AccountList();
        //No initial withdrawal limit
        testAccountList.addAccount("Byebye", "10010", "");
        assertThrows(NumberFormatException.class, 
                () -> testAccountList.setWithdrawalLimit("ab"));
        assertEquals(null, testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());

        //With initial withdrawal limit
        assertDoesNotThrow(() -> testAccountList.setWithdrawalLimit("11000"));
        assertThrows(NumberFormatException.class, 
                () -> testAccountList.setWithdrawalLimit(""));
        assertEquals("11000", testAccountList.getMainAccount()
                .getWithdrawalChecker().getWithdrawalLimit());
    }
}
