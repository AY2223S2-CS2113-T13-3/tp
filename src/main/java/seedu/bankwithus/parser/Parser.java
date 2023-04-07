package seedu.bankwithus.parser;

import seedu.bankwithus.exceptions.CorruptedTransactionFileException;
import seedu.bankwithus.exceptions.MoreThanTwoDecimalPlace;
import seedu.bankwithus.exceptions.NoValueInputException;
import seedu.bankwithus.exceptions.TransactionFileIsEmptyException;
import seedu.bankwithus.user.AccountList;
import seedu.bankwithus.BankWithUs;
import seedu.bankwithus.user.Transaction;
import seedu.bankwithus.user.TransactionList;
import seedu.bankwithus.storage.TransactionDecoder;
import seedu.bankwithus.ui.Ui;
import seedu.bankwithus.exceptions.AccountNotFoundException;
import seedu.bankwithus.exceptions.CommandNotFoundException;
import seedu.bankwithus.exceptions.CorruptedSaveFileException;
import seedu.bankwithus.exceptions.ExceedsWithdrawalLimitException;
import seedu.bankwithus.exceptions.InsufficientBalanceException;
import seedu.bankwithus.exceptions.NegativeAmountException;
import seedu.bankwithus.exceptions.NoAccountException;
import seedu.bankwithus.exceptions.NoTransactionsFoundException;
import seedu.bankwithus.exceptions.SaveFileIsEmptyException;
import seedu.bankwithus.exceptions.WithdrawalCancelledException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    private BankWithUs bwu;
    private AccountList accountList;
    private TransactionList transactionList;
    private Ui ui;
    /**
     * Instantiates a bwu Parser object.
     *
     * @param bwu the main bankWithUs program
     */
    public Parser(BankWithUs bwu) {
        this.bwu = bwu;
        this.ui = bwu.getUi();
        this.accountList = bwu.getAccountList();
        this.transactionList = bwu.getTransactionList();
    }

    /**
     * Instantiates a accountList Parser object.
     *
     * @param accountList the accountList
     */
    public Parser(AccountList accountList) {
        this.accountList = accountList;
    }

    public Parser(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Parses the user input into command and arguments.
     *
     */
    public void parseUserInput(String input) throws CommandNotFoundException, IOException {
        // Split input by space
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
<<<<<<< HEAD
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
                    transactionList.createNewTransaction(accountList.getMainAccount().getAccountName(),
                            "deposit", args, LocalDate.now());
                    ui.showDepositMessage();
                    accountList.showBal();
                    ui.printLine();
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
                try {
                    String accDetails = accountList.getAllAccountDetails();
                    ui.viewAccount(accDetails);
                } catch (AccountNotFoundException e) {
                    ui.showAccountNotFound();
                }
                break;
            case "withdraw":
                try {
                    accountList.withdrawMoney(args);
                    transactionList.createNewTransaction(accountList.getMainAccount().getAccountName(),
                            "withdraw", args, LocalDate.now());
                    accountList.showBal();
                    ui.printLine();
                } catch (NumberFormatException e) {
                    ui.showNumberFormatError();
                } catch (NegativeAmountException e) {
                    ui.showNegativeAmountError();
                } catch (InsufficientBalanceException e) {
                    ui.showInsufficientBalanceMessage();
                } catch (ExceedsWithdrawalLimitException e) {
                    ui.showExceedsWithdrawalLimitError();
                    String[] wlInfo = accountList.checkWithdrawalLimit();
                    ui.showWithdrawalLimit(wlInfo[0]); //print wl
                    ui.showTotalAmountWithdrawn(wlInfo[1]); //print total amt withdrawn
                    ui.printLine();
                }
                break;
            case "add-account":
                accountList.createNewAccount();
                break;
            case "switch-to":
                try {
                    accountList.switchMainAccount(args);
                } catch (NoAccountException e) {
                    ui.showAccountNotFound();
                }
                break;
            case "set-wl":
                try {
                    accountList.setWithdrawalLimit(args);
                    String withdrawalLimit = accountList.getMainAccount()
                            .getWithdrawalChecker().getWithdrawalLimit();
                    ui.showWithdrawalLimitSet(withdrawalLimit);
                    ui.printLine();
                } catch (NumberFormatException e) {
                    ui.showNumberFormatError();
                } catch (NegativeAmountException e) {
                    ui.showNegativeAmountError();
                }
                break;
            case "check-wl":
=======
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
                transactionList.createNewTransaction(accountList.getMainAccount().getAccountName(),
                        "deposit", args, LocalDate.now());
                ui.showDepositMessage();
                accountList.showBal();
                ui.printLine();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (NullPointerException e) {
                // Will almost never happen, but who knows
                ui.showNullInputError();
            } catch (NegativeAmountException e) {
                ui.showNegativeAmountError();
            } catch (MoreThanTwoDecimalPlace e) {
                ui.showDecimalPlacesError();
            }
            break;
        case "view-account":
            try {
                String accDetails = accountList.getAllAccountDetails();
                ui.viewAccount(accDetails);
            } catch (AccountNotFoundException e) {
                ui.showAccountNotFound();
            }
            break;
        case "withdraw":
            try {
                accountList.withdrawMoney(args);
                transactionList.createNewTransaction(accountList.getMainAccount().getAccountName(),
                        "withdraw", args, LocalDate.now());
                accountList.showBal();
                ui.printLine();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (NegativeAmountException e) {
                ui.showNegativeAmountError();
            } catch (InsufficientBalanceException e) {
                ui.showInsufficientBalanceMessage();
            } catch (ExceedsWithdrawalLimitException e) {
                ui.showExceedsWithdrawalLimitError();
>>>>>>> 5d19a837abbe8039203ad4db889582c026cfe4ca
                String[] wlInfo = accountList.checkWithdrawalLimit();
                ui.showWithdrawalLimit(wlInfo[0]); //print wl
                ui.showTotalAmountWithdrawn(wlInfo[1]); //print total amt withdrawn
                ui.printLine();
<<<<<<< HEAD
                break;
            case "help":
                ui.showHelp();
                break;
            case "set-save-goal":
                if(args.length() > 0) {
                    String untilWhenStr = ui.getDeadline();
                    accountList.handleSaveGoal(args, untilWhenStr);
                } else {
                    ui.showInsufficientArgsEntered();
                }
                break;
            case "show-save-goal":
                accountList.showGoal();
                break;
            case "delete":
                accountList.deleteAccount(args);
                break;
            case "view-transactions-all":
                try {
                    transactionList.printAllTransactions();
                    ui.printLine();
                } catch (NoTransactionsFoundException e) {
                    ui.noTransactionsFoundError();
                }
                break;
            default:
                throw new CommandNotFoundException();
=======
            } catch (WithdrawalCancelledException e) {
                ui.showWithdrawCancelled();
                ui.printLine();
            } catch (MoreThanTwoDecimalPlace e) {
                ui.showDecimalPlacesError();
            } catch (NoValueInputException e) {
                ui.showNoValueInput();
            }
            break;
        case "add-account":
            accountList.createNewAccount();
            break;
        case "switch-to":
            try {
                accountList.switchMainAccount(args);
            } catch (NoAccountException e) {
                ui.showAccountNotFound();
            }
            break;
        case "set-wl":
            try {
                accountList.setWithdrawalLimit(args);
                String withdrawalLimit = accountList.getMainAccount()
                        .getWithdrawalChecker().getWithdrawalLimit();
                ui.showWithdrawalLimitSet(withdrawalLimit);
                ui.printLine();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (NegativeAmountException e) {
                ui.showNegativeAmountError();
            }
            break;
        case "check-wl":
            String[] wlInfo = accountList.checkWithdrawalLimit();
            ui.showWithdrawalLimit(wlInfo[0]); //print wl
            ui.showTotalAmountWithdrawn(wlInfo[1]); //print total amt withdrawn
            ui.printLine();
            break;
        case "help":
            ui.showHelp();
            break;
        case "set-save-goal":
            if(args.length() > 0) {
                String untilWhenStr = ui.getDeadline();
                accountList.handleSaveGoal(args, untilWhenStr);
            } else {
                ui.showInsufficientArgsEntered();
            }
            break;
        case "show-save-goal":
            accountList.showGoal();
            break;
        case "delete":
            accountList.deleteAccount(args);
            break;
        case "view-transactions-all":
            try {
                transactionList.printAllTransactions();
                ui.printLine();
            } catch (NoTransactionsFoundException e) {
                ui.noTransactionsFoundError();
            }
            break;
        default:
            throw new CommandNotFoundException();
>>>>>>> 5d19a837abbe8039203ad4db889582c026cfe4ca
        }
    }

    //@@author Sherlock-YH
    /**
     * Parses the save file. Takes in the scanner to the save file.
     * Splits the name and balance by ; character.
     * Part of AccountList parser, not bwu parser.
     *
     * @throws CorruptedSaveFileException if any of the parameters are corrupted
     */
    public void parseSavedFile(Scanner scanner) throws CorruptedSaveFileException, SaveFileIsEmptyException {
        while (scanner.hasNextLine()) {
            String accountDetails = scanner.nextLine();
            if (accountDetails.isBlank()) {
                throw new SaveFileIsEmptyException();
            }
            String[] splitDetails = accountDetails.split(";");
            try {
                String name = splitDetails[0].trim();
                String balanceString = splitDetails[1].trim();
                String totalAmtWithdrawn = splitDetails[2].trim();
                String lastWithdrawnDate = splitDetails[3].trim();
                String withdrawalLimit = splitDetails[4].trim();
                String amtToSave;
                String untilWhenStr;
                if (splitDetails.length > 5) {
                    amtToSave = splitDetails[5].trim();
                    untilWhenStr = splitDetails[6].trim();
                } else {
                    amtToSave = "0";
                    untilWhenStr = "2001-01-01";
                }
                LocalDate untilWhen = LocalDate.parse(untilWhenStr);
                if (name.isEmpty() || balanceString.isEmpty() || totalAmtWithdrawn.isEmpty()) {
                    throw new CorruptedSaveFileException();
                }
                if (lastWithdrawnDate.isEmpty()) {
                    //if no history of withdrawing
                    accountList.addAccount(name, balanceString, withdrawalLimit, amtToSave, untilWhen);
                } else {
                    accountList.addAccount(name, balanceString, totalAmtWithdrawn,
                            LocalDate.parse(lastWithdrawnDate), withdrawalLimit, amtToSave, untilWhen);
                }
            } catch (Exception e) {
                throw new CorruptedSaveFileException();
            }
        }
        scanner.close();
        if (accountList.getSize() == 0){
            throw new SaveFileIsEmptyException();
        }
    }

    /**
     * Reads the transaction data from the transaction save file.
     * @param scanner
     * @throws CorruptedSaveFileException
     * @throws SaveFileIsEmptyException
     */
    public void parseTransactionFile(Scanner scanner) throws CorruptedTransactionFileException,
            TransactionFileIsEmptyException {
        boolean isCorrupted = false;
        while (scanner.hasNextLine()) {
            String transactionDetails = scanner.nextLine();
            try {
                if (transactionDetails.isBlank()) {
                    throw new SaveFileIsEmptyException();
                }
                TransactionDecoder decoder = new TransactionDecoder();
                Transaction temp = decoder.decodeTransaction(transactionDetails);
                transactionList.addTransaction(temp);
            } catch (Exception e) {
                isCorrupted = true;
            }
        }
        if (isCorrupted) {
            throw new CorruptedTransactionFileException();
        }
        scanner.close();
        if (transactionList.getSize() == 0) {
            throw new TransactionFileIsEmptyException();
        }
    }
}

