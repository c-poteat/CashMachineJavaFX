package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public class BasicAccount extends Account {

    public BasicAccount(AccountData accountData) {
        super(accountData);
    }

    @Override
    protected boolean canWithdraw(float amount) {
        return false;
    }


}



