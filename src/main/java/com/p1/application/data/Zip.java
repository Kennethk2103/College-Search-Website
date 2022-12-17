package com.p1.application.data;

import java.io.Serializable;


public class Zip implements Serializable{
	
	private int zip;
	private double longitude;
	private double latitude;

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Zip(int zip, Double double1, Double double2) {
		super();
		this.zip = zip;
		this.longitude = double1;
		this.latitude = double2;
	}

}
