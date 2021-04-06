package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class InvalidException extends RuntimeException{
    public InvalidException(String msg) {
        super(msg);
    }
}
