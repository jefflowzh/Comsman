package util.exception;

public class CustomerEmailExistException extends Exception {

    public CustomerEmailExistException() {
    }

    public CustomerEmailExistException(String msg) {
        super(msg);
    }
}