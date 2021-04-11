package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import javassist.NotFoundException;

public class NoGameActiveException extends NotFoundException {
    public NoGameActiveException(String msg){
        super(msg);
        }
}
