package chapter02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(final String line){
        final String[] columns = line.split(",");
        final LocalDate date = LocalDate.parse(columns[0], DATE_TIME_FORMATTER);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(final List<String> lines){
        return lines.stream().map(this::parseFrom).toList();
    }

}
