import example.model.AccountHolder;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

class SimpleBankAccountTest extends SimpleBankAccountWithFeeTest {

    private static final double FEE = 0;

    @BeforeEach
    void beforeEach() {
        var accountHolder = new AccountHolder("Mario", "Rossi", 1);
        var bankAccount = new SimpleBankAccount(accountHolder, 0);
        super.beforeEach(accountHolder, bankAccount);
    }

    @Test
    void testDeposit() {
        super.testDeposit(FEE);
    }

    @Test
    void testWrongDeposit() {
        super.testWrongDeposit(FEE);
    }

    @Test
    void testWithdraw() {
        super.testWithdraw(FEE);
    }

    @Test
    void testWrongWithdraw() {
        super.testWrongWithdraw(FEE);
    }
}
