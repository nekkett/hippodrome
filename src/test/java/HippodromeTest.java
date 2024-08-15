import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    void hippodromeParamNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           new Hippodrome(null);
        }) ;

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void hippodromeParamListNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome( Collections.emptyList());
        });

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesVerifyReturn(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Horse" + i, i);
            horses.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        List<Horse> returnedList = hippodrome.getHorses();

        assertEquals(horses.size(), returnedList.size());

        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), returnedList.get(i));
        }
    }

    @Test
    void getWinnerReturnedMax(){
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 200),
                new Horse("Cherry", 3, 300)
        );

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(300.0, hippodrome.getWinner().getDistance());
    }

    @Test
    void  moveAllHorsesVerify(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses){
            verify(horse).move();
        }
    }


}
