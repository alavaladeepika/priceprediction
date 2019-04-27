package org.iiitb.spe.priceprediction.model;

public class RequestObject {
	private double pickup_latitude;
	private double pickup_longitude;
	private double drop_latitude;
	private double drop_longitude;
	private int passenger_count;
	private String date;
	private String time;
	
	public RequestObject() {
		
	}
	
	public RequestObject(double pickup_latitude, double pickup_longitude, 
			double drop_latitude, double drop_longitude, int passenger_count, String date, String time) {
		this.setDate(date);
		this.setDrop_latitude(drop_latitude);
		this.setDrop_longitude(drop_longitude);
		this.setPassenger_count(passenger_count);
		this.setPickup_latitude(pickup_latitude);
		this.setPickup_longitude(pickup_longitude);
		this.setTime(time);
	}
	
	public double getPickup_latitude() {
		return pickup_latitude;
	}
	public void setPickup_latitude(double pickup_latitude) {
		this.pickup_latitude = pickup_latitude;
	}
	public double getPickup_longitude() {
		return pickup_longitude;
	}
	public void setPickup_longitude(double pickup_longitude) {
		this.pickup_longitude = pickup_longitude;
	}
	public double getDrop_latitude() {
		return drop_latitude;
	}
	public void setDrop_latitude(double drop_latitude) {
		this.drop_latitude = drop_latitude;
	}
	public double getDrop_longitude() {
		return drop_longitude;
	}
	public void setDrop_longitude(double drop_longitude) {
		this.drop_longitude = drop_longitude;
	}
	public int getPassenger_count() {
		return passenger_count;
	}
	public void setPassenger_count(int passenger_count) {
		this.passenger_count = passenger_count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		double p = 0.017453292519943295; // Pi by 180
		double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;
		return 0.6213712 * 12742 * Math.asin(Math.sqrt(a)); // 2*R*asin...	
	}
	
	public int isLate_night(int hour) {
	    if ((hour <= 6) || (hour >= 20)){
	        return 1;
	    }
	    else {
	        return 0;
	    }
	}

	public int isNight (int hour, int weekday) {
	    if (((hour <= 20) && (hour >= 16)) && (weekday < 5)){
	        return 1;
	    }
	    else {
	        return 0;
	    }
	}
	
}
