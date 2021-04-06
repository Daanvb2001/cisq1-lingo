package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class WrongWordLengthException extends RuntimeException {
    public WrongWordLengthException(String msg) {
        super(msg);
    }
}
