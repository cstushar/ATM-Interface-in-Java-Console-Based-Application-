package in.ineuron.dto;

import java.util.Date;

//It is a dto layer which help to transfer the transaction data between classes
public class AccountTransaction {
    private int transactionId;
    private Date date;
    private String description;
    private double amount;

    public AccountTransaction(int transactionId, Date date, String description, double amount) {
        this.transactionId = transactionId;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
