import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBankAccountWithFeeTest {
    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private final int amountToDeposit = 100;
    private final int amountToWithdraw = 70;
    private final int wrongUserId = 2;

    void beforeEach(AccountHolder accountHolder, BankAccount bankAccount) {
        this.accountHolder = accountHolder;
        this.bankAccount = bankAccount;
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    void testDeposit(final double fee) {
        bankAccount.deposit(accountHolder.getId(), this.amountToDeposit);
        assertEquals(this.amountToDeposit - fee, bankAccount.getBalance());
    }

    void testWrongDeposit(final double fee) {
        int amountToDepositWithWrongID = 50;
        bankAccount.deposit(accountHolder.getId(), this.amountToDeposit);
        bankAccount.deposit(this.wrongUserId, amountToDepositWithWrongID);
        assertEquals(this.amountToDeposit - fee, bankAccount.getBalance());
    }

    void testWithdraw(final double fee) {
        final int numberOfTransactions = 2;
        bankAccount.deposit(accountHolder.getId(), this.amountToDeposit);
        bankAccount.withdraw(accountHolder.getId(), this.amountToWithdraw);
        assertEquals(
            this.amountToDeposit - this.amountToWithdraw - fee * numberOfTransactions,
            bankAccount.getBalance()
        );
    }

    void testWrongWithdraw(final double fee) {
        bankAccount.deposit(accountHolder.getId(), this.amountToDeposit);
        bankAccount.withdraw(this.wrongUserId, this.amountToWithdraw);
        assertEquals(this.amountToDeposit - fee, bankAccount.getBalance());
    }
}
