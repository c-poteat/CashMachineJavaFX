package rocks.zipcode.atm.bank;

import java.text.DecimalFormat;

/**
 * @author ZipCodeWilmington
 */
public abstract class Account<amount> {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    DecimalFormat df = new DecimalFormat();


    private AccountData accountData;

    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    public static Float getBalance(Float amount) {
        return amount;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void deposit(Float amount) {
        updateBalance(getBalance() + amount);
        }

        public boolean withdraw (Float amount){
                if (canWithdraw(amount)) {
                    updateBalance(getBalance() - amount);
                    return true;
                } else {
                    return false;
                }
            }

            protected boolean canWithdraw (Float amount){
                return getBalance() >= amount;
            }

            public Float getBalance () {
                return accountData.getBalance();
            }

            private void updateBalance (Float newBalance){
                accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),
                        newBalance);
            }
            protected abstract boolean canWithdraw ( float amount);

}

