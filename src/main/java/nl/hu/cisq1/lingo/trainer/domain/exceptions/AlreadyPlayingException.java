package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class AlreadyPlayingException extends DuplicateKeyException {
    public AlreadyPlayingException(String msg) {
        super(msg);
    }
}
