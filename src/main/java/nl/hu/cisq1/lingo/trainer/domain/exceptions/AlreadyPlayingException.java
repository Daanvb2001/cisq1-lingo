package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class AlreadyPlayingException extends RuntimeException {
    public AlreadyPlayingException(String msg) {
        super(msg);
    }
}
