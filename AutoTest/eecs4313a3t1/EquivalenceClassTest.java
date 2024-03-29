package eecs4313a3t1;

import static org.junit.Assert.*;
import org.junit.Test;
import net.sf.borg.common.*;

public class EquivalenceClassTest {

	@Test
	public void testMinuteString() {
		//Hour
		//Class 1: Mins/60 = 1 and Mins%60 = 0 - Testing 1 hour [Range: [1]]
		assertEquals("1 Hour",DateUtil.minuteString(60));
		//Class 2: Mins/60 = 1 and Mins%60 = 1 -Testing 1 hour with 1 minute [Range:[1] hour and [1] minute]
		assertEquals("1 Hour 1 Minute",DateUtil.minuteString(61));
		//Class 3: Mins/60 = 1 and Mins%60 > 1 -Testing 1 hour with some minutes [Range:[1] hour and (1,59] minutes]
		assertEquals("1 Hour 15 Minutes",DateUtil.minuteString(75));
		//--------------------------------------------------------------------------------------------------------------
		//Hours
		//Class 4: Mins/60 > 1 and Mins%60 = 0 -Testing hours more than one [Range:(1, infinity) hours] 
		assertEquals("3 Hours",DateUtil.minuteString(180));
		//Class 5: Mins/60 > 1 and Mins%60 = 1 -Testing hours more than 1 hour with some minutes [Range:(1,infinity) hours and [1] minute]
		assertEquals("2 Hours 1 Minute",DateUtil.minuteString(121));
		//Class 6: Mins/60 > 1 and Mins%60 > 1 -Testing hours more than 1 hour with some minutes [Range:(1,infinity) hours and (1,59] minutes]
		assertEquals("2 Hours 25 Minutes",DateUtil.minuteString(145));
		//-----------------------------------------------------------------------------------------------------------------
		// Minutes
		//Class 7: Mins/60 = 0 and Mins%60 = 0 -Testing 0 minutes [Range:[0] minute]
		assertEquals("0 Minutes",DateUtil.minuteString(0));
		//Class 8: Mins/60 = 0 and Mins%60 = 1 -Testing 1 minute [Range:[1] minute]
		assertEquals("1 Minute",DateUtil.minuteString(1));
		//Class 9: Mins/60 = 0 and Mins%60 > 1 - Testing minutes that are less than 1 hour [Range: (1,59] minutes]
		assertEquals("50 Minutes",DateUtil.minuteString(50));
		// ----------------------------------------------------------------------------------------------------------------
		//based off the implmentation of negative inputs in the method [ Invalid inputs]
		//Class 10: Mins/60 < 0 and Mins%60 = 1 - Testing negative hours with negative minutes [Range:[(0,negative infinity)hour and [-1] minutes]
		assertEquals("1 Minute",DateUtil.minuteString(-61)); // (-61) mod 60 = 1 but the output is -1 
		//Class 11 : Mins/60 < 0 and Mins%60 > 1 - Teseing -1 minute [Range:[(0,negative infinity)hour and [-1] minute]
		assertEquals("10 Minutes",DateUtil.minuteString(-70)); // (-71) mod 60 = 10 but the output is -10	
	}
}
