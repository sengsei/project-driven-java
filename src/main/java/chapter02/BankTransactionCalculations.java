package chapter02;

import java.time.Month;
import java.util.List;

public class BankTransactionCalculations {
    private final List<BankTransaction> bankTransactions;

    public BankTransactionCalculations(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.
                stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    private static double selectInMonth(List<BankTransaction> bankTransactions, final Month month) {
        return bankTransactions.stream()
                .filter(e -> month.equals(e.getDate().getMonth()))
                .mapToDouble(BankTransaction::getAmount).sum();
    }
}
