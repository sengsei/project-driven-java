package chapter03;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HtmlExporter();
        final Exporter jsonExporter = new JsonExporter();

        bankStatementAnalyzer.analyze(bankStatementParser, exporter);
        bankStatementAnalyzer.analyze(bankStatementParser, jsonExporter);

    }
}
