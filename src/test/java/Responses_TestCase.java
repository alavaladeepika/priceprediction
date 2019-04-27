import org.iiitb.spe.priceprediction.model.*;
import org.iiitb.spe.priceprediction.services.*;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Responses_TestCase {
	
	@Test
	public void testResponse() throws FileNotFoundException, IOException, ParseException {
		RequestObject obj = new RequestObject(40.12, -73.456, 42.134, -74.5678, 2, "2016-10-12", "23:00:00");
		PriceServices pserv = new PriceServices();
		float estimate = Math.round(pserv.get_estimate(obj)*100);
		assertEquals(estimate/100.0, 11.52, 0.1);
	}
}
