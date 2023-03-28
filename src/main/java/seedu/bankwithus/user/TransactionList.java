package seedu.bankwithus.user;

import seedu.bankwithus.exceptions.CorruptedSaveFileException;
import seedu.bankwithus.exceptions.NoTransactionsFoundException;
import seedu.bankwithus.exceptions.SaveFileIsEmptyException;
import seedu.bankwithus.parser.Parser;
import seedu.bankwithus.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionList {

    private ArrayList<Transaction> transactions;
    private int size;

    public TransactionList() {
        this.transactions = new ArrayList<>();
        this.size = 0;
    }

    public TransactionList(Scanner scanner) {
        this.size = 0;
        transactions = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser(this);
        try {
            parser.parseTransactionFile(scanner);
        } catch (CorruptedSaveFileException e) {
            ui.showCorruptedSaveFileError();
        } catch (SaveFileIsEmptyException e) {
            ui.showEmptyFile();
        }
    }

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
