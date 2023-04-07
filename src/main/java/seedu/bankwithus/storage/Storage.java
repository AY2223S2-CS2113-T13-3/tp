package seedu.bankwithus.storage;

import seedu.bankwithus.user.AccountList;
import seedu.bankwithus.user.TransactionList;
import seedu.bankwithus.exceptions.AccountNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    protected File accountFile;
    protected File transactionFile;
    private final File saveDir = new File("data");


    /**
     * Creates a new instance of Storage. Initialises the saveFile.
     *
     * @param accountsFilepath the accounts filepath. Should be data/save.txt by default
     * @param transactionsFilepath the transactions filepath. Should be data/transaction.txt by default
     */
    public Storage(String accountsFilepath, String transactionsFilepath) {
        this.accountFile = new File(accountsFilepath);
        this.transactionFile = new File(transactionsFilepath);
    }

    /**
     * Loads the saveFile contexts into AccountList.
     *
     * @return the scanner containing the contents of the saveFile
     * @throws FileNotFoundException if file is not found
     */
    public Scanner loadAccounts() throws FileNotFoundException {
        return new Scanner(accountFile);
    }

    //@@author xiaoge26
    public Scanner loadTransactions() throws FileNotFoundException {
        return new Scanner(transactionFile);
    }

    //@@author
    /**
     * Creates a new saveFile if file is not found. Also creates the data directory.
     *
     * @throws IOException if something goes really wrong. Should almost never happen
     */
    public void createNewAccountsFile() throws IOException {
        saveDir.mkdir();
        accountFile.createNewFile();
    }


    //@@author xiaoge26
    //Take note that it does not create a new directory if it does not exist as
    //the data directory is created in the createNewAccountsFile() method
    //It does not show creation message as well, as it is not necessary
    public void createNewTransactionsFile() throws IOException {
        transactionFile.createNewFile();
    }


    //@@author Sherlock-YH
    /**
     * This method saves all account details to data/save.txt.
     *
     * @param list The AccountList that stores all accounts
     */
    public void saveToFile(AccountList list) throws IOException {
        FileWriter fw = new FileWriter(accountFile);
        try {
            fw.write(list.getAllAccountDetails());
            fw.close();
        } catch (AccountNotFoundException e) {
            fw.close();
        }
    }

    //@@author xiaoge26
    /**
     * This method saves all transaction details to data/save.txt.
     *
     * @param transactionList The TransactionList that stores all transactions
     */
    public void saveTransactionsToFile(TransactionList transactionList) throws IOException {
        FileWriter fw = new FileWriter(transactionFile);
        TransactionEncoder transactionEncoder = new TransactionEncoder();
        fw.write(transactionEncoder.encodeTransactionList(transactionList));
        fw.close();
    }
}
