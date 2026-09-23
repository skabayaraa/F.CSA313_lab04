import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(95.0);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void eightyFiveIsB() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(85.0);

        // Assert
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void seventyFiveIsC() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(75.0);

        // Assert
        assertEquals("C", grade);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(65.0);

        // Assert
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(30.0);

        // Assert
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("0 оноо F дүн байх ёстой")
    void zeroIsF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(0.0);

        // Assert
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("100 оноо A дүн байх ёстой")
    void oneHundredIsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(100.0);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("Сөрөг оноо оруулахад exception шидэх ёстой")
    void negativeScoreThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1)
        );
    }

    @Test
    @DisplayName("100-аас их оноо оруулахад exception шидэх ёстой")
    void scoreOverOneHundredThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101)
        );
    }

    @Test
    @DisplayName("Бүх онооны нийлбэр 100 гарах ёстой")
    void totalScoreIsOneHundred() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double total = calc.totalScore(10, 40, 10, 10, 30);

        // Assert
        assertEquals(100.0, total);
    }

    @Test
    @DisplayName("Сөрөг ирцийн оноонд exception шидэх ёстой")
    void negativeAttendanceThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабораторийн оноо 40-өөс их бол exception шидэх ёстой")
    void labOverLimitThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("Хязгаарын оноонуудаар үсгэн дүнг шалгах")
    @CsvSource({
            "95, A",
            "90, A",
            "89.99, B",
            "80, B",
            "75, C",
            "70, C",
            "65, D",
            "60, D",
            "59.99, F",
            "0, F",
            "100, A"
    })
    void letterGradeBoundaries(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String actual = calc.letterGrade(score);

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Нийлбэр оноог зөв тооцох")
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "5, 20, 5, 5, 15, 50",
            "0, 0, 0, 0, 0, 0",
            "10, 30, 8, 7, 25, 80"
    })
    void totalScoreCalculatesCorrectly(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double actual = calc.totalScore(att, lab, quiz1, quiz2, exam);

        // Assert
        assertEquals(expected, actual);
    }
}