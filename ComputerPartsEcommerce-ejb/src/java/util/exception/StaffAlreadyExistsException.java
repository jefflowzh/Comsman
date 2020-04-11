package util.exception;

public class StaffAlreadyExistsException extends Exception {

    public StaffAlreadyExistsException() {
    }

    public StaffAlreadyExistsException(String message) {
        super(message);
    }
}
