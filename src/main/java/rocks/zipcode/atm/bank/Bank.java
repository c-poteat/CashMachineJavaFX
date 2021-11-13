package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1, new BasicAccount(new AccountData(
                1, "Grim Reaper", "grimreaper@gmail.com", 5000
        )));

        accounts.put(2, new BasicAccount(new AccountData(
                2, "Mad Max", "madmax3000@gmail.com", 6500
        )));
        accounts.put(3, new PremiumAccount(new AccountData(
                3, "Darth Vader", "dVader@gmail.com", 1000000
        )));

        accounts.put(4, new PremiumAccount(new AccountData(
                4, "John Snow", "jsnow@gmail.com", 554
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());

        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account numbers 1, 2, 3, 4");
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
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
