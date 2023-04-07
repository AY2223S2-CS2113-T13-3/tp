package seedu.bankwithus;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import seedu.bankwithus.common.WithdrawalChecker;

public class WithdrawalCheckerTest {
    @Test
    public void updateTotalAmtWithdrawn_validInput_noException() {
        WithdrawalChecker wcNoWithdrawals = new WithdrawalChecker();
        //test without withdrawals
        assertDoesNotThrow(() -> wcNoWithdrawals.updateTotalAmtWithdrawn(new BigDecimal("10.1")));
        assertEquals("10.1", wcNoWithdrawals.getTotalAmtWithdrawn());

        //test with prior withdrawals
        assertDoesNotThrow(() -> wcNoWithdrawals.updateTotalAmtWithdrawn(new BigDecimal("1.1")));
        assertEquals("11.2", wcNoWithdrawals.getTotalAmtWithdrawn());

        WithdrawalChecker wcWithWithdrawals = new WithdrawalChecker("10.01",
                LocalDate.parse("2023-01-10"));

        //test with prior withdrawals in a different month
        assertDoesNotThrow(() -> wcWithWithdrawals.updateTotalAmtWithdrawn(new BigDecimal("1.11")));
        assertEquals("1.11", wcWithWithdrawals.getTotalAmtWithdrawn());
    }

    @Test
    public void toStringTest() {
        WithdrawalChecker wc = new WithdrawalChecker();
        //test without withdrawal and withdrawal limit
        assertEquals("0; ; ", wc.toString());
        
        //test without withdrawal and with withdrawal limit
        assertDoesNotThrow(() -> wc.setWithdrawalLimit((float) 10.10));
        assertEquals("0; ;10.1", wc.toString());

        //test with withdrawal and without withdrawal limit
        WithdrawalChecker wc2 = new WithdrawalChecker("1000", LocalDate.parse("2023-01-10"));
        assertEquals("1000;2023-01-10; ", wc2.toString());

        //test with withdrawal and with withdrawal limit
        assertDoesNotThrow(() -> wc2.setWithdrawalLimit((float) 100.10));
        assertEquals("1000;2023-01-10;100.1", wc2.toString());
    }
}
