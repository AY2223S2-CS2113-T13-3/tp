package seedu.bankwithus.user;

import seedu.bankwithus.exceptions.CorruptedTransactionFileException;
import seedu.bankwithus.exceptions.NoTransactionsFoundException;
import seedu.bankwithus.exceptions.TransactionFileIsEmptyException;
import seedu.bankwithus.parser.Parser;
import seedu.bankwithus.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionList {

    private final ArrayList<Transaction> transactions;
    private int size;

    public TransactionList() {
        this.transactions = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Creates a new instance of TransactionList. Initialises transactions.
     * Should load data into transactions list too.
     *
     * @param scanner the scanner to read the transaction file
     * @throws CorruptedTransactionFileException thrown when the transaction file is corrupted
     * @throws TransactionFileIsEmptyException thrown when the transaction file is empty
     */
    public TransactionList(Scanner scanner) {
        this.size = 0;
        transactions = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser(this);
        try {
            parser.parseTransactionFile(scanner);
        } catch (CorruptedTransactionFileException e) {
            ui.showCorruptedTransactionFileError();
        } catch (TransactionFileIsEmptyException e) {
            //shows "No transactions found!" as this catch block
            ui.showNoTransactionsFoundMessage();
        }
    }


    /**
     * Creates a new transaction and adds it to the transaction list.
     *
     * @param accountName the name of the account
     * @param type the type of transaction
     * @param amount the amount of money involved in the transaction
     * @param date the date of the transaction
     */
    public void createNewTransaction(String accountName, String type, String amount, LocalDate date) {
        Transaction transaction = new Transaction(accountName, type, amount, date);
        assert transaction != null;
        transactions.add(transaction);
        size++;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        size++;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    public int getSize() {
        return size;
    }

    public void printAllTransactions() throws NoTransactionsFoundException {
        if (size == 0) {
            throw new NoTransactionsFoundException();
        }
        for (int i = 0; i < size; i++) {
            System.out.println(transactions.get(i).toString());
        }
    }
}
