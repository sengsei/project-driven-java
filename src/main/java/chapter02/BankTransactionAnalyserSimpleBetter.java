package chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;



public class BankTransactionAnalyserSimpleBetter {
    private static final String RESOURCES = "src/main/resources/";


    public static void main(final String[] args) throws IOException {
        BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction>  bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

        System.out.println("Total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Total for Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.
                stream().map(BankTransaction::getAmount).reduce(0.0, Double::sum);
    }

    private static double selectInMonth(List<BankTransaction> bankTransactions, final Month month) {
        return bankTransactions.stream()
                .filter(e -> month.equals(e.getDate().getMonth()))
                .map(BankTransaction::getAmount)
                .reduce(0.0, Double::sum);
    }
}
