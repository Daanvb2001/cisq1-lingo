package nl.hu.cisq1.lingo.trainer.domain.exceptions;

public class NoGameActive extends RuntimeException {
    public NoGameActive(String msg){
        super(msg);
        }
}
