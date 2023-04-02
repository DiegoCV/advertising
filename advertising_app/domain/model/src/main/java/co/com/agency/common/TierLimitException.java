package co.com.agency.common;

public class TierLimitException extends RuntimeException{
    public TierLimitException() {
        super("Tier Limit up");
    }
    public TierLimitException(String message) {
        super(message);
    }

}
