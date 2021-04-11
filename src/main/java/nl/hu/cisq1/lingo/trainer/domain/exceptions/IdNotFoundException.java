package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String msg) {
        super(msg);
    }
}
