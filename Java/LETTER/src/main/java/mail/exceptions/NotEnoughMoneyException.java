package mail.exceptions;

public class NotEnoughMoneyException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotEnoughMoneyException(String e) {
        super(e);
    }
}