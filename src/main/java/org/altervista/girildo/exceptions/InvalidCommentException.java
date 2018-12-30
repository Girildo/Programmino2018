package org.altervista.girildo.exceptions;

public class InvalidCommentException extends Exception {

    public InvalidCommentException() {
    }

    public InvalidCommentException(String message) {
        super(message);
    }

    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommentException(Throwable cause) {
        super(cause);
    }
}
