package org.altervista.girildo.exceptions;

public class InvalidVoteException extends Exception {

    public InvalidVoteException() {
    }

    public InvalidVoteException(String message) {
        super(message);
    }

    public InvalidVoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVoteException(Throwable cause) {
        super(cause);
    }
}
