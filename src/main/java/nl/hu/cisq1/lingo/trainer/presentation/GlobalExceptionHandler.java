package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyPlayingException.class)
    public ResponseEntity<ExceptionResponse> alreadyPlayingException(AlreadyPlayingException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("CONFLICT");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(GameHasEndedException.class)
    public ResponseEntity<ExceptionResponse> gameHasEndedException(GameHasEndedException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("CONFLICT");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> idNotFoundException(IdNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("NOT FOUND");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ExceptionResponse> invalidException(InvalidException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("NOT FOUND");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoGameActiveException.class)
    public ResponseEntity<ExceptionResponse> noGameActiveException(NoGameActiveException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("NOT FOUND");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRoundStartedException.class)
    public ResponseEntity<ExceptionResponse> noRoundStartedException(NoRoundStartedException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("NOT FOUND");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotPlayingException.class)
    public ResponseEntity<ExceptionResponse> notPlayingException(NotPlayingException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("NOT FOUND");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongWordLengthException.class)
    public ResponseEntity<ExceptionResponse> wrongWordLengthException(WrongWordLengthException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("CONFLICT");
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
    }


}
