package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(10, new BasicAccount(new AccountData(
                10, "Darth Vader", "deathstar@gmail.com", 1500.25f
        )));

        accounts.put(20, new PremiumAccount(new AccountData(
                20, "Clark Kent", "dailyplanet@gmail.com", 2156.56f
        )));
        accounts.put(30, new BasicAccount(new AccountData(
                30, "Norman Bates", "batesmotel@gmail.com", -298.23f
        )));
        accounts.put(40, new PremiumAccount(new AccountData(
                40, "Marty McFly", "backIIthefuture@gmail.com", 6008.58f

        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());

        } else {
            return ActionResult.fail("No account with id: " + id + "\nPlease input account numbers 10, 20, 30, 40");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, Float amount) {
            Account account = accounts.get(accountData.getId());
            account.deposit(amount);

                return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
//        } else if(amount < 0) {
//            return ActionResult.fail("Withdraw failed: Cannot withdraw negative numbers");

        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }

    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
}

