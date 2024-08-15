import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest
{
    @Disabled
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testTimeoutMain(){
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
