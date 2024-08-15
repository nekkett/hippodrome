import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
 class HorseTest {
    @Test
    void horseNameNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           new Horse(null, 0, 0);
        });

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r", "\f", "\u2003"})
    void horseNameBlank(String blank){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(blank, 0,0);
        });

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void horseSpeedNegative(){
        IllegalArgumentException exception  = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("1", -2, 0);
        });

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void horseDistanceNegative(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("1",0, -1);
        });

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameReturnedVerify(){
        Horse horse = new Horse("1", 0);

        assertEquals("1", horse.getName());
    }

    @Test
    void getSpeedReturnedVerify(){
        Horse horse = new Horse("1", 1);

        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistanceReturnedVerify() {
        Horse horse = new Horse("1", 1, 2);

        assertEquals(2, horse.getDistance());

        Horse horse1 = new Horse("1", 1);

        assertEquals(0, horse1.getDistance());
    }

    @Test
    void moveGetRandomDoubleVerify(){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("1", 2, 3);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9), times(1));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 10, 5",
            "0.3, 15, 4.5",
            "0.7, 20, 14"
    })
    void moveGetRandomDoubleReturn(double randomDouble, double speed, double expectedDistance){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("1", speed);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.001);
        }
    }
}
