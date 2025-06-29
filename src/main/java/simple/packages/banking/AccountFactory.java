package simple.packages.banking;

public interface AccountFactory {
    public abstract BankAccount getBankAccount(int id, double balance, String accountName);
}
