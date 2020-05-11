package exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(){
        super("Bank does not own account");
    }
}
