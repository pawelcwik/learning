package pl.com.clockworkgnome.pragmaticunittesting.first;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {

        // Arrange
        // Given
        ScoreCollection scores = new ScoreCollection();
        scores.add(() -> 5 );
        scores.add(() -> 7);

        // Act
        // When
        int actualResult = scores.arithmeticMean();

        // Assert
        // Then
        assertThat(actualResult,equalTo(6));

        assertEquals(actualResult, 6);

        assertTrue(actualResult==6);
    }

}