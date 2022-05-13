package chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);

        collectSummary(bankStatementCalc);    }


    private static void collectSummary(final BankStatementCalc bankStatementCalc) {
        System.out.println("The total for all transactions is "
                + bankStatementCalc.calculateTotalAmount());
        System.out.println("The total for transactions in January is "
                + bankStatementCalc.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The max transaction in January is " + bankStatementCalc.findHighestTransactionInMonth(Month.JANUARY).getAsDouble());
        System.out.println("The total for transactions in February is "
                + bankStatementCalc.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is "
                + bankStatementCalc.calculateTotalForCategory("Salary"));
        System.out.println("Total amount Grouped by description field: ");
        bankStatementCalc.totalAmountGroupedByDescription().entrySet().forEach(System.out::println);
    }

}
