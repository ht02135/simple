package simple.packages.banking;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class BankTransfer {
    public static void main(String[] args) {
        AccountFactory accountFactory = BankAccount::new;
        BankAccount studentBankAccount = accountFactory.getBankAccount(1, 50000, "StudentA");
        BankAccount universityBankAccount = accountFactory.getBankAccount(2, 100000, "University");

        BiPredicate<Double, Double> hasEnoughBalancePredicate = (balance, amount) -> balance > amount;
        BiConsumer<String, Double> displayTransactionMessage = (x, y) -> System.out.println(x + y);
        BiConsumer<BankAccount, BankAccount> displayBalanceMessage = (student, university) ->
                System.out.println("Balance Message : Ending balance of student account: " + studentBankAccount.getBalance() +
                        " University account: " + universityBankAccount.getBalance());

        //setup pool of 5 thread
        ExecutorService service = Executors.newFixedThreadPool(50);

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " :: Executing Transfer");
            try {
                double amount = 10;
                if (!hasEnoughBalancePredicate.test(studentBankAccount.getBalance(), amount)) {
                    displayTransactionMessage.accept(Thread.currentThread().getName() + " Transaction Message : :: balance insufficient, ", amount);
                    return;
                }
                //transfer amount to universityBankAccount
                while (!studentBankAccount.transfer(universityBankAccount, amount)) {
                    TimeUnit.MILLISECONDS.sleep((int)(Math.random() * 100));
                    continue;
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            displayTransactionMessage.accept(Thread.currentThread().getName() + " Transaction Message transfer is successful: universityBankAccount Balance in account ",
                    universityBankAccount.getBalance());
            
            System.out.println(Thread.currentThread().getName() + " :: Transfer is done");
        });

        //submit() submits a Callable or a Runnable task to an ExecutorService (pool of 5 threrad)
        //and returns a result of type Future:
        for (int i = 0; i < 50; i++) {
        	System.out.println("Running task i="+i);
        	Future future = service.submit(t1);
        	try {
        		//just curious what future obj is...
				System.out.println("future.get() = " + future.get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
        }
        /*
        1>shutdown:
        Initiates an orderly shutdown in which previously submitted tasks are executed, but no new 
        tasks will be accepted. 
        2>awaitTermination:
        Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, 
        or the current thread is interrupted, whichever happens first.
        3>Conclusion
        You should call shutdown first. Otherwise, you might be waiting for a very long time, since
         awaitTermination doesn't actually shut down your executor.
        */
        service.shutdown();

        try {
        	//awaitTermination will just sit there until the timeout runs out
            while (!service.awaitTermination(10, TimeUnit.MINUTES)) {
                System.out.println("Not Yet. Still waiting for termination");
            }
        } catch (InterruptedException iee) {
            iee.printStackTrace();
        }
        displayBalanceMessage.accept(studentBankAccount, universityBankAccount);
    }
}

