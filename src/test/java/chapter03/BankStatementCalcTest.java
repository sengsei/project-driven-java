package chapter03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BankStatementCalcTest {

    @Test
    public void shouldCalculateTotalInFebruary() {
        final BankTransaction februarySalary
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 14), 2000, "Salary");

        final BankTransaction februaryRoyalties
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 20), 500, "Royalties");

        final List<BankTransaction> bankTransactions
                = List.of(februarySalary,
                februaryRoyalties);

        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);
        final double amountFeb
                = bankStatementCalc.calculateTotalInMonth(Month.FEBRUARY);

        assertThat(amountFeb).isEqualTo(2500);
    }

    @Test
    public void shouldFindTransactionsGreaterThanEqual1000() {
        final BankTransaction februarySalary
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 14), 2000, "Salary");

        final BankTransaction marchRoyalties
                = new BankTransaction(LocalDate.of(2019, Month.MARCH, 20), 500, "Royalties");

        final List<BankTransaction> bankTransactions
                = List.of(februarySalary,
                marchRoyalties);

        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);
        final List<BankTransaction> transactions
                = bankStatementCalc.findTransactionsGreaterThanEqual(1000);

        assertThat(transactions).contains(februarySalary);
        assertThat(transactions).hasSize(1);
    }

    @Test
    public void shouldFilterTransactionsInFebruary() {

        final BankTransaction februarySalary
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 14), 2000, "Salary");

        final BankTransaction marchRoyalties
                = new BankTransaction(LocalDate.of(2019, Month.MARCH, 20), 500, "Royalties");

        final List<BankTransaction> bankTransactions
                = List.of(februarySalary,
                marchRoyalties);

        final BankStatementCalc bankStatementCalc = new BankStatementCalc(bankTransactions);
        final List<BankTransaction> transactions
                = bankStatementCalc.findTransactions(new BankTransactionIsInFebruaryAndExpensive());

        assertThat(transactions).contains(februarySalary);
        assertThat(transactions).hasSize(1);

    }


    static class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
        @Override
        public boolean test(final BankTransaction bankTransaction) {
            return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000;
        }
    }
}