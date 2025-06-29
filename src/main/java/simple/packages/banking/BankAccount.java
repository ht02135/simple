package simple.packages.banking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;

public class BankAccount {
    private int id;
    private double balance;
    private String accountName;
    private final Lock lock = new ReentrantLock();
    
    private BiFunction<Double, Double, Double> substractOperation = (a,b) -> a-b;
    private BiFunction<Double, Double, Double> addOperation = (a,b) -> a + b;

    public BankAccount (int id, double balance, String accountName){
        this.id = id;
        this.balance=balance;
        this.accountName = accountName;
    }

    public boolean withdraw (double amount) throws InterruptedException {
    	/*
Lock lock = ...;
  if (lock.tryLock()) {
      try {
          // manipulate protected state
      } finally {
          lock.unlock();
      }
  } else {
      // perform alternative actions
  }
    	*/
        if(this.lock.tryLock()){
            try {
            	//sleep in milliseconds
            	int sleepMilliSec = (int)(Math.random() * 10);
            	System.out.println("deposit sleepMilliSec="+sleepMilliSec);
                Thread.sleep(sleepMilliSec);
                balance = substractOperation.apply(balance, amount);
                //sleep in milliseconds
                Thread.sleep(sleepMilliSec);
            } finally {
            	this.lock.unlock();
            }
            return true;
        }
        return false;
    }

    public boolean deposit (double amount) throws InterruptedException {
    	
        if(this.lock.tryLock()){
        	System.out.println("deposit obtain lock");
            try {
            	//sleep in milliseconds
            	int sleepMilliSec = (int)(Math.random() * 10);
            	System.out.println("deposit sleepMilliSec="+sleepMilliSec);
                Thread.sleep(sleepMilliSec);
                balance= addOperation.apply(balance,amount);
                //sleep in milliseconds
                Thread.sleep(sleepMilliSec);
            } finally {
            	this.lock.unlock();
            }
            return true;
        }
        System.out.println("deposit CANT obtain lock");
        return false;
    }

    public boolean transfer (BankAccount to, double amount) throws InterruptedException {
        if(withdraw(amount)){ //withdraw from this account
            System.out.println("Withdrawing amount: " + amount + " from: " + getAccountName());
            if(to.deposit(amount)){
                System.out.println("Depositing amount:" + amount + " to: " + to.getAccountName());
                return true;
            }else{
                System.out.println("Failed to acquire both locks: refunding " + amount + " to: " + accountName);
                while (!deposit(amount)) //refund to this account
                    continue;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", accountName='" + accountName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
