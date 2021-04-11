package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import javassist.NotFoundException;

public class NotPlayingException extends NotFoundException {
    public NotPlayingException(String msg) {
        super(msg);
    }
}
