package seedu.bankwithus;

import seedu.bankwithus.exceptions.CommandNotFoundException;
import seedu.bankwithus.exceptions.CorruptedSaveFileException;
import seedu.bankwithus.exceptions.InsufficientBalanceException;
import seedu.bankwithus.exceptions.NegativeAmountException;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private BankWithUs bwu;
    private AccountList accountList;
    private Ui ui;

    /**
     * Instantiates a bwu Parser object
     * 
     * @param bwu the main bankWithUs program
     */
    public Parser(BankWithUs bwu) {
        this.bwu = bwu;
        this.ui = bwu.getUi();
        this.accountList = bwu.getAccountList();
    }

    /**
     * Instatiates a accountList Parser object
     * 
     * @param accountList the accountList
     */
    public Parser(AccountList accountList) {
        this.accountList = accountList;
    }

    /**
     * Parses the user input into command and arguments.
     * @throws IOException
     */
    public void parseUserInput(String input) throws CommandNotFoundException, IOException {
        // Split input by space
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
        case "exit":
            try {
                bwu.exit();
            } catch (IOException e) {
                throw e;
            }
            break;
        case "deposit":
            try {
                accountList.depositMoney(args);
                ui.showDepositMessage();
                accountList.showBal();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (NullPointerException e) {
                // Will almost never happen, but who knows
                ui.showNullInputError();
            } catch (NegativeAmountException e) {
                ui.showNegativeAmountError();
            }
            break;
        case "view-account":
            String accDetails = accountList.getAllAccountDetails();
            ui.viewAccount(accDetails);
            break;
        case "withdraw":
            try {
                accountList.withdrawMoney(args);
                ui.showWithdrawMessage();
                accountList.showBal();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (NegativeAmountException e) {
                ui.showNegativeAmountError();
            } catch (InsufficientBalanceException e) {
                ui.showInsufficientBalanceMessage();
            }
            break;
        case "help":
            ui.showHelp();
            break;
        default:
            throw new CommandNotFoundException();
        }
    }

    /**
     * Parses the save file. Takes in the scanner to the save file,
     * and splits the name and balance by ; character. Part of 
     * accountList parser, not bwu parser
     * 
     * @param scanner
     * @throws CorruptedSaveFileException if any of the parameters are corrupted
     */
    public void parseSavedFile(Scanner scanner) throws CorruptedSaveFileException {
        String accountDetails = scanner.nextLine();
        accountDetails.trim();
        if (accountDetails.isBlank()) {
            throw new CorruptedSaveFileException();
        }
        try {
            String[] splitDetails = accountDetails.split(";");
            String name = splitDetails[0].trim();
            String balanceString = splitDetails[1].trim();
            if (name.isEmpty() || balanceString.isEmpty()) {
                throw new Exception();
            }
            float balance = Float.parseFloat(balanceString);
            accountList.addAccount(name, balance);
        } catch (Exception e) {
            throw new CorruptedSaveFileException();
        }
        scanner.close();
    }
}
