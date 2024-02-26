import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBankAccountWithFeeTest {
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    void beforeEach(AccountHolder accountHolder, BankAccount bankAccount) {
        this.accountHolder = accountHolder;
        this.bankAccount = bankAccount;
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    void testDeposit(final double fee) {
        bankAccount.deposit(accountHolder.getId(), 100);
        assertEquals(100 - fee, bankAccount.getBalance());
    }

    void testWrongDeposit(final double fee) {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.deposit(2, 50);
        assertEquals(100 - fee, bankAccount.getBalance());
    }

    void testWithdraw(final double fee) {
        final int numberOfTransactions = 2;
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(accountHolder.getId(), 70);
        assertEquals(30 - fee * numberOfTransactions, bankAccount.getBalance());
    }

    void testWrongWithdraw(final double fee) {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(2, 70);
        assertEquals(100 - fee, bankAccount.getBalance());
    }
}
