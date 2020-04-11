package util.exception;

public class StaffAlreadyExistsException extends Exception {

    public StaffAlreadyExistsException() {
    }

    public StaffAlreadyExistsException(String msg) {
        super(msg);
    }
}
