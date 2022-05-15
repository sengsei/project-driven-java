package chapter03;


import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionInFebruaryAndExpensiveTest {
    BankTransaction t1 = new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 2), 1000, "Deliveroo");
    BankTransaction t2 = new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 3), 999,"Tesco");
    List<BankTransaction> bankTransactions = List.of(t1,t2);
    BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);



    @Test
    void shouldGetTransactionInFebAndExpensive(){
        final List<BankTransaction> transactions = bankStatementCalc.findTransactions(new BankTransactionInFebruaryAndExpensive());
        final double result = transactions.get(0).getAmount();
        final double expected = 1000;

        assertEquals(expected, result);
    }

}