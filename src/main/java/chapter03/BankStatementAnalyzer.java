package chapter03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final BankStatementParser bankStatementParser, final Exporter exporter) throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementCalc.summaryStatistics();

        System.out.println(exporter.export(summaryStatistics));

    }

}
