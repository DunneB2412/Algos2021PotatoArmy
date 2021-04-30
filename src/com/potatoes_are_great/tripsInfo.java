package com.potatoes_are_great;

import java.util.ArrayList;

/**
 * @author bsharkey
 */
public class tripsInfo {
	public ArrayList<Integer> tripID;
	public ArrayList<Integer> arrivalTimes;
	public ArrayList<Integer> departureTimes;
	public ArrayList<Integer> stopID;
	public ArrayList<Integer> stopSequence;
	public ArrayList<Integer> pickupType;
	public ArrayList<Integer> dropoffType;
	public ArrayList<Double> distTravelled;
	
	public tripsInfo(ArrayList<Integer> tripID, ArrayList<Integer> arrivalTimes, ArrayList<Integer> departureTimes, ArrayList<Integer> stopID, 
			ArrayList<Integer> stopSequence, ArrayList<Integer> pickupType,
			ArrayList<Integer> dropoffType, ArrayList<Double> distTravelled) {
		
		this.tripID = tripID;
		this.arrivalTimes = arrivalTimes;
		this.departureTimes = departureTimes;
		this.stopID = stopID;
		this.stopSequence = stopSequence;
		this.pickupType = pickupType;
		this.dropoffType = dropoffType;
		this.distTravelled = distTravelled;
	}
}
