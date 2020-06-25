package by.kharitonov.task4_1.exception;

public class IntegerArrayException extends Exception {
    public IntegerArrayException() {
    }

    public IntegerArrayException(String message) {
        super(message);
    }

    public IntegerArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegerArrayException(Throwable cause) {
        super(cause);
    }
}
