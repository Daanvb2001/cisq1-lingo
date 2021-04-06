package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    private static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("brood", "baard", "b....", "b...d"),
                Arguments.of("breek", "baard", "b...d", "b...d"),
                Arguments.of("brand", "baard", "b...d", "b.a.d"),
                Arguments.of("baard", "baard", "b.a.d", "baard")
                );
    }

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback=new Feedback("baard","baard");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed(){
        Feedback feedback=new Feedback("kaart","baard");
        assertFalse(feedback.isWordGuessed());
    }


    @Test
    @DisplayName("word is invalid if all letters are invalid")
    void wordIsInvalid(){
        assertThrows(InvalidException.class,()->new Feedback("blaren", "kaart"));
    }

    @Test
    @DisplayName("word is valid if no letters are invalid")
    void wordIsvalid(){
        Feedback feedback=new Feedback("kaart","baard");
        assertDoesNotThrow(() -> new Feedback("kaart", "baard"));
    }

    @Test
    @DisplayName("feedback is different if values are different")
    void feedbackIsDifferent(){
        Feedback feedbackA=new Feedback("kaart","kaart");
        Feedback feedbackB=new Feedback("baard", "kaart");
        assertNotEquals(feedbackA,feedbackB);
    }

    @Test
    @DisplayName("feedback is same if values are the same")
    void feedbackIsSame(){
        Feedback feedbackA=new Feedback("kaart","kaart");
        Feedback feedbackB=new Feedback("kaart","kaart");
        assertEquals(feedbackA,feedbackB);
    }

    @Test
    @DisplayName("marks are the same")
    void equalsTest(){
        Feedback feedbackA=new Feedback("kaart","kaart");
        Feedback feedbackB=new Feedback("kaart","kaart");
        assertEquals(true,feedbackA.equals(feedbackB));
    }

    @Test
    @DisplayName("marks are not the same")
    void equalsTestFalse(){
        Feedback feedbackA=new Feedback("kaart","kaart");
        Feedback feedbackB=new Feedback("baard","kaart");
        assertEquals(false,feedbackA.equals(feedbackB));
    }
    //    @ParameterizedTest
//    @DisplayName("Hint is correct")
//    @MethodSource("provideHintExamples")
//    void givehintTest(String attempt, String wordToGuess){
//        Feedback feedbackA=new Feedback(attempt);
//        Round round=new Round(wordToGuess);
////        feedbackA.giveHint();
//        assertEquals(feedbackA.giveHint(attempt),("b...."));
//    }

    @Test
    @DisplayName("toString contains class name")
    void convertedToString() {
        Feedback feedbackA = new Feedback("kaart", "baard");
        assertTrue(feedbackA.toString().contains("Feedback"));
    }

    @Test
    @DisplayName("Hashcode is correct")
    void HashcodeTest() {
        Feedback feedbackA=new Feedback("kaart","kaart");
        Feedback feedbackB=new Feedback("kaart","kaart");
        assertEquals(feedbackA.hashCode(),feedbackB.hashCode());
    }



    @ParameterizedTest
    @DisplayName("correct guess")
    @MethodSource("provideHintExamples")
    void gethintTest(String attempt, String wordToGuess, String previousHint, String result){
        Feedback feedbackA=new Feedback(attempt, wordToGuess);
        assertEquals(result,feedbackA.getHint(previousHint));
    }

}