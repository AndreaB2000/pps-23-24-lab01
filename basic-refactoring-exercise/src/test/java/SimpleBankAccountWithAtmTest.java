import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import example.model.AccountHolder;
import example.model.SimpleBankAccountWithAtm;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountWithFeeTest {
    
    private static final double FEE = SimpleBankAccountWithAtm.FEE;

    @BeforeEach
    void beforeEach() {
        var accountHolder = new AccountHolder("Mario", "Rossi", 1);
        var bankAccount = new SimpleBankAccountWithAtm(accountHolder, 0);
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
