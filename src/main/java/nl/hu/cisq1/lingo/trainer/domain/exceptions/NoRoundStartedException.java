package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import javassist.NotFoundException;

public class NoRoundStartedException extends NotFoundException {
    public NoRoundStartedException(String msg){super(msg);}
}
