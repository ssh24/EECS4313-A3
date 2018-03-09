package eecs4313a3t1;

import static org.junit.Assert.*;
import org.junit.Test;
import net.sf.borg.common.*;
import java.util.Date;

public class DecisionTableTest {

    @Test
    public void testIsAfter() {
        Date d1 = new Date(117, 11, 3);
        Date d2 = new Date(117, 11, 3);
        boolean result;
        
        // date d1 is equal to d2
        result = DateUtil.isAfter(d1, d2);
        assertFalse("Date d1 is equal to d2", result);
        
        // date d1 is before d2
        d1.setDate(2);
        result = DateUtil.isAfter(d1, d2);
        assertFalse("Date d1 is before d2", result);
        
        // date d1 is after d2
        d1.setDate(4);
        result = DateUtil.isAfter(d1, d2);
        assertTrue("Date d1 is after d2", result);
    }

}
