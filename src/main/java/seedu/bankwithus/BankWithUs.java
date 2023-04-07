package seedu.bankwithus;

import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.bankwithus.user.AccountList;
import seedu.bankwithus.user.TransactionList;
import seedu.bankwithus.exceptions.CommandNotFoundException;
import seedu.bankwithus.parser.Parser;
import seedu.bankwithus.storage.Storage;
import seedu.bankwithus.ui.Ui;

public class BankWithUs {

    public static final String ACCOUNTS_FILE_PATH = "data/save.txt";
    public static final String TRANSACTIONS_FILE_PATH = "data/transaction.txt";
    public boolean isExitEntered = false;
    private Storage storage;
    private Ui ui;
    private AccountList accountList;

    private TransactionList transactionList;
    private Parser parser;

    /**
     * Creates a new instance of BankWithUs. Initialises storage, ui,
     * accounts and transactions. Should load data into accounts and transaction list too.
     *
     * @param accountsFilePath the accounts filepath. Should be data/save.txt by default
     * @param transactionsFilePath the transactions filepath. Should be data/transaction.txt by default
     * @throws IOException thrown when something goes really, really wrong
     */
    public BankWithUs(String accountsFilePath, String transactionsFilePath) throws IOException {
        // Main object instantiations
        ui = new Ui();
        storage = new Storage(accountsFilePath, transactionsFilePath);

        // Ui stuff
        ui.createScanner();
        ui.greet();

        // Initialising accountList
        try {
            accountList = new AccountList(storage.loadAccounts(), this);
            ui.showNumberOfAccount(accountList.getSize());
        } catch (FileNotFoundException e) {
            // If saveFile not created
            ui.showFileNotFoundError();
            try {
                storage.createNewAccountsFile();
                ui.showFileCreated();
            } catch (IOException ioE) {
                ui.showIOError();
                throw ioE;
            }
            accountList = new AccountList(this);
        }

        try {
            transactionList = new TransactionList(storage.loadTransactions());
        } catch (FileNotFoundException e) {
            // If transactionFile not created
            ui.showFileNotFoundError();
            try {
                storage.createNewTransactionsFile();
            } catch (IOException ioE) {
                ui.showIOError();
                throw ioE;
            }
            transactionList = new TransactionList();
        }
        parser = new Parser(this);
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public TransactionList getTransactionList() {
        return transactionList;
    }

    public Ui getUi() {
        return ui;
    }

    //@@author Sherlock-YH
    /**
     * Exit the programme, save the data and show farewell message.
     *
     * @throws IOException throw error if the data cannot be saved
     */
    public void exit() throws IOException {
        isExitEntered = true;
        ui.showFarewellMessage();
        ui.closeScanner();
        try {
            storage.saveToFile(accountList);
            storage.saveTransactionsToFile(transactionList);
        } catch (IOException e) {
            ui.showIOError();
            throw e;
        }
    }
    //@@author tyuyang
    /**
     * The main command and output loop. Takes in user input line by line
     * and gives it to the parser to execute the command.
     *
     * @throws IOException if something goes wrong while exiting the program
     */
    public void run() throws IOException {
        while (!isExitEntered) {
            String line = ui.getNextLine();
            try {
                parser.parseUserInput(line);
            } catch (CommandNotFoundException e) {
                ui.showCommandNotFoundError();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new BankWithUs(ACCOUNTS_FILE_PATH, TRANSACTIONS_FILE_PATH).run();
        } catch (IOException e) {
            return;
        }
    }
}
