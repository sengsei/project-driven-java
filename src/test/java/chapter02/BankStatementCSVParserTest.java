package chapter02;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {
    private final BankStatementParser bankStatementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "30-01-2017,-100,Deliveroo";

        final BankTransaction result = bankStatementParser.parseFrom(line);
        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo");

        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount());
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseLinesFromFile(){
        List<String> lines = List.of("30-01-2017,-100,Deliveroo","30-01-2017,-50,Tesco");

        final List<BankTransaction> result = bankStatementParser.parseLinesFrom(lines);

        BankTransaction t1 = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo");
        BankTransaction t2 = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50,"Tesco");

        List<BankTransaction> expected = List.of(t1, t2);

        assertEquals(expected,result);
    }

}