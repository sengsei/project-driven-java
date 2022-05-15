package chapter03;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

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

    public Map<String, Double> totalAmountGroupedByDescription() {
        return bankTransactions.stream()
                .collect(groupingBy(BankTransaction::getDescription, summingDouble(BankTransaction::getAmount)));
    }
}
