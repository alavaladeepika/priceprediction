package org.iiitb.spe.priceprediction.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.iiitb.spe.priceprediction.model.Model;
import org.iiitb.spe.priceprediction.model.RequestObject;

@Path("/price")
public class PriceServices {

    @Path("/get_estimate")
    @POST
    @Consumes("application/json")
    public double get_estimate(RequestObject obj) throws FileNotFoundException, IOException, ParseException {
    	
    	String path = "/usr/local/tomcat/webapps/priceprediction/WEB-INF/classes/org/iiitb/spe/priceprediction/saved_model.txt";
        Model model = new Model(path);
        
        ArrayList<Double> test_case = new ArrayList<Double>();
        test_case.add(obj.getPickup_longitude());
        test_case.add(obj.getPickup_latitude());
        test_case.add(obj.getDrop_longitude());
        test_case.add(obj.getDrop_latitude());
        test_case.add((double) obj.getPassenger_count());
        
        test_case.add(obj.distance(obj.getPickup_latitude(), obj.getPickup_longitude(), obj.getDrop_latitude(), obj.getDrop_longitude()));
        
        double[] nyc = {-74.0063889, 40.7141667};
        test_case.add(obj.distance(nyc[1], nyc[0], obj.getDrop_latitude(), obj.getDrop_longitude()));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = sdf.parse(obj.getDate()+" "+obj.getTime());
        Calendar calender = Calendar.getInstance();
        calender.setTime(datetime);
        
        test_case.add((double) calender.get(Calendar.HOUR_OF_DAY));
        test_case.add((double) calender.get(Calendar.YEAR));
        test_case.add((double) calender.get(Calendar.DAY_OF_MONTH));
        
        int weekday = ((calender.get(Calendar.DAY_OF_WEEK))+5)%7;
        test_case.add((double) weekday);
        test_case.add((double) obj.isNight(calender.get(Calendar.HOUR_OF_DAY), weekday));
        test_case.add((double) obj.isLate_night(calender.get(Calendar.HOUR_OF_DAY)));
        
        //System.out.println(test_case.toString());
        
        return model.predict(test_case);
        
    }
}
