package seedu.bankwithus.storage;

import seedu.bankwithus.user.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TransactionDecoder {

    public Transaction decodeTransaction(String encodedTransaction) {
        //code quality recs
        String[] split;
        split = encodedTransaction.split(";");
        if (split.length == 4) {
            String accountName = split[0];
            String type = split[1];
            String amount = split[2];
            LocalDate date = LocalDate.parse(split[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new Transaction(accountName, type, amount, date);
        } else {
            throw new IllegalArgumentException("Invalid transaction format: " + encodedTransaction);
        }
    }
}
