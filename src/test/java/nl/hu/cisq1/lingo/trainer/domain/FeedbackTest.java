package nl.hu.cisq1.lingo.trainer.domain;

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
                Arguments.of("brand", "baard","b...d", "b.a.d"),
                Arguments.of("baard", "baard","b.a.d", "baard")
                );
    }
//
//    @Test
//    @DisplayName("word is guessed if all letters are correct")
//    void wordIsGuessed(){
//        Feedback feedback=new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        assertTrue(feedback.isWordGuessed());
//    }
//
//    @Test
//    @DisplayName("word is not guessed if not all letters are correct")
//    void wordIsNotGuessed(){
//        Feedback feedback=new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        assertFalse(feedback.isWordGuessed());
//    }
//
//    @Test
//    @DisplayName("word is invalid if all letters are invalid")
//    void wordIsInvalid(){
//        Feedback feedback=new Feedback(List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
//        assertTrue(feedback.isWordValid());
//    }
//
//    @Test
//    @DisplayName("word is valid if no letters are invalid")
//    void wordIsvalid(){
//        Feedback feedback=new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        assertFalse(feedback.isWordValid());
//    }
//
//    @Test
//    @DisplayName("feedback is different if values are different")
//    void feedbackIsDifferent(){
//        Feedback feedbackA=new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        Feedback feedbackB=new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID));
//        assertNotEquals(feedbackA,feedbackB);
//    }
//
//    @Test
//    @DisplayName("feedback is same if values are the same")
//    void feedbackIsSame(){
//        Feedback feedbackA=new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        Feedback feedbackB=new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
//        assertEquals(feedbackA,feedbackB);
//    }

//    @ParameterizedTest
//    @DisplayName("Hint is correct")
//    @MethodSource("provideHintExamples")
//    void givehintTest(String attempt, String wordToGuess){
//        Feedback feedbackA=new Feedback(attempt);
//        Round round=new Round(wordToGuess);
////        feedbackA.giveHint();
//        assertEquals(feedbackA.giveHint(attempt),("b...."));
//    }

    @ParameterizedTest
    @DisplayName("correct guess")
    @MethodSource("provideHintExamples")
    void givehintTest(String attempt, String wordToGuess, String previousHint, String result){
        Feedback feedbackA=new Feedback(attempt);
        Round round=new Round(wordToGuess);
//        feedbackA.giveHint();
        assertEquals(result,feedbackA.giveHint(wordToGuess,previousHint));
    }

}