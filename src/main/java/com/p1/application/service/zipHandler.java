package com.p1.application.service;


import com.p1.application.data.Zip;
import com.p1.application.data.zipTable;




public class zipHandler{

	
	public static double distance(Zip zip, double lat2, double lon2) {
		double lon1 = zip.getLongitude();
		double lat1 = zip.getLatitude();
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      return dist;
	    }

	    private static double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    private static double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }
	

}
