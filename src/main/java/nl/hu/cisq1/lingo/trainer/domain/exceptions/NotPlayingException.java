package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class NotPlayingException extends RuntimeException{
    public NotPlayingException(String msg) {
        super(msg);
    }
}
