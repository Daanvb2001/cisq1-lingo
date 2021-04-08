package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class idNotFoundException extends RuntimeException{
    public idNotFoundException(String msg) {
        super(msg);
    }
}
