package seedu.bankwithus.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String accountName;
    private String amount;

    //Take Note of this date is in LocalDate format, but not a String!
    private LocalDate date;
    private String type;

    public Transaction(String accountName, String type, String amount, LocalDate date) {
        this.accountName = accountName;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        assert date != null;
        return "Account Name: " + accountName
                + " Transaction Type: " + type
                + " Amount: " + amount
                + " Date: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String getAccountName() {
        return accountName;
    }

    public String getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getDateStr() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getType() {
        return type;
    }
}
