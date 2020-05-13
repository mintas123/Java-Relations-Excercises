package unique;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static utils.Utils.checkIfNull;

public class BankAccount {

    private static final String ACCOUNTS = "accounts";
    private static final String CARDS = "cards";


    private static Set<Long> uniqueAccounts = new HashSet<>();
    private static Set<Long> uniqueCards = new HashSet<>();
    private static Map<String, Set<Long>> mapOfUnique = Map.of(ACCOUNTS, uniqueAccounts,
                                                               CARDS, uniqueCards);


    //not unique
    private String owner;
    private double balance;
    private LocalDate startTime;

    //unique
    private long accountNumber;
    private Set<Long> creditCards = new HashSet<>();

    public BankAccount(String owner, double balance, LocalDate startTime, long accountNumber) {
        setOwner(owner);
        setStartTime(startTime);
        setAccountNumber(accountNumber);
        this.balance = balance;
    }

    private void setAccountNumber(long accountNumber) {
        if (mapOfUnique.get(ACCOUNTS).contains(accountNumber)) {
            throw new IllegalArgumentException("account number already in use! Not unique.");
        }
        this.accountNumber = accountNumber;
        uniqueAccounts.add(accountNumber);
    }

    public void addCreditCard(long creditCard) {
        if (mapOfUnique.get(CARDS).contains(creditCard)) {
            throw new IllegalArgumentException("Card already registered! Not unique.");
        }
        this.creditCards.add(creditCard);
        uniqueCards.add(creditCard);
    }

    public void removeCreditCard(long creditCard) {
        if (!mapOfUnique.get(CARDS).contains(creditCard)) {
            throw new IllegalArgumentException("No such  card registered!");
        }
        if (!this.creditCards.contains(creditCard)) {
            throw new IllegalArgumentException("User doesnt have such card!");
        }
        this.creditCards.remove(creditCard);
        uniqueCards.remove(creditCard);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        checkIfNull(owner);
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        checkIfNull(startTime);
        this.startTime = startTime;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Set<Long> getCreditCards() {
        return Collections.unmodifiableSet(creditCards);
    }

    public static Map<String, Set<Long>> getMapOfUnique() {
        return Collections.unmodifiableMap(mapOfUnique);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                ", startTime=" + startTime +
                ", accountNumber=" + accountNumber +
                ", creditCards=" + creditCards +
                '}';
    }
}
