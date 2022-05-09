package chapter02;

import java.time.Month;
import java.util.List;
import java.util.OptionalDouble;

public class BankStatementCalc {
    private final List<BankTransaction> bankTransactions;

    public BankStatementCalc(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.
                stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalInMonth(final Month month) {
        return bankTransactions.stream()
                .filter(e -> month.equals(e.getDate().getMonth()))
                .mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalForCategory(String salary) {
        return bankTransactions.stream()
                .filter(e -> salary.equals(e.getDescription()))
                .mapToDouble(BankTransaction::getAmount).sum();
    }

    public OptionalDouble findHighestTransactionInMonth(final Month month){
        return  bankTransactions.stream()
                .filter(e -> month.equals(e.getDate().getMonth()))
                .mapToDouble(BankTransaction::getAmount).max();
    }
}
