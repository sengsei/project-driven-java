package chapter02;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCalcTest {
    BankTransaction t1 = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo");
    BankTransaction t2 = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50,"Tesco");
    List<BankTransaction> bankTransactions = List.of(t1,t2);
    BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);

    @Test
    void shouldCalculateTotalAmount() {
        final double result = bankStatementCalc.calculateTotalAmount();
        final double expected = -150;

        assertEquals(expected, result);
    }

    @Test
    void shouldCalculateTotalInMonth() {
        final Month month = Month.JANUARY;
        final double result = bankStatementCalc.calculateTotalInMonth(month);
        final double expected = -150;

        assertEquals(expected, result);
    }

    @Test
    void shouldCalculateTotalForCategory() {
        final String category = "Tesco";
        final double result = bankStatementCalc.calculateTotalForCategory(category);
        final double expected = -50;

        assertEquals(expected, result);
    }

    @Test
    void findHighestTransactionInMonth() {
        final Month month = Month.JANUARY;
        final OptionalDouble result = bankStatementCalc.findHighestTransactionInMonth(month);
        final double expected = -50;

        assertEquals(expected, result.getAsDouble());
    }

    @Test
    void totalAmountGroupedByDescription() {
        final Map<String, Double> result = bankStatementCalc.totalAmountGroupedByDescription();
        final Map<String, Double> expected = new HashMap<>();
        expected.put("Deliveroo",-100.0);
        expected.put("Tesco", -50.0);

        assertEquals(expected,result);
    }
}