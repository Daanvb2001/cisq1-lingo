package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import javassist.NotFoundException;

public class IdNotFoundException extends NotFoundException {
    public IdNotFoundException(String msg) {
        super(msg);
    }
}
