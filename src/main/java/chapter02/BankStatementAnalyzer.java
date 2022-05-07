package chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction>bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);

        collectSummary(bankStatementCalc);
    }

    private static void collectSummary(final BankStatementCalc bankStatementCalc) {
        System.out.println("The total for all transactions is "
                + bankStatementCalc.calculateTotalAmount());
        System.out.println("The total for transactions in January is "
                + bankStatementCalc.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for transactions in February is "
                + bankStatementCalc.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is "
                + bankStatementCalc.calculateTotalForCategory("Salary"));
    }

}
