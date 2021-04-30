package com.potatoes_are_great;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class tripFinder {
	
	int tripID[];
	int arrivalTimes[];
	int departureTimes[];
	int stopID[];
	int stopSequence[];
	int pickupType[];
	int dropoffType[];
	double distTravelled[];
	
	
	tripFinder() throws IOException{
		
		//get the space needed
		int numberOfLines = count("stop_times.txt");
		
		//initialize the arrays to the appropriate size
		tripID = new int[numberOfLines];
		arrivalTimes = new int[numberOfLines];
		departureTimes = new int[numberOfLines];
		stopID = new int[numberOfLines];
		stopSequence = new int[numberOfLines];
		pickupType = new int[numberOfLines];
		dropoffType = new int[numberOfLines];
		distTravelled  = new double[numberOfLines];
		
		readInData("stop_times.txt");
		
	}
	
	//counts the number of lines in a txt file;
	public int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if(endsWithoutNewLine) {
	            ++count;
	        } 
	        return count;
	    } finally {
	        is.close();
	    }
	}
	
	//reads in all the data
	public void readInData(String filename) {
		
		String timeStr[] = new String[3];
		int hours;
		int minutes;
		int seconds;
		
		try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
		    String str;
		    String data[] = new String[9];
		    int currentLine = 0;
		    while ((str = in.readLine()) != null) {
		    	
		    	
		       
		    	//skips the fist line which is just the headers
		    	if(currentLine != 0) {
			    	data = str.split(",");
			    	
			    	for(int i = 0; i < data.length; i++) {
			    		if(data[i] == null || data[i] == "") {
			    			data[i] = "0";
			    		}
			    	}
			        
			        tripID[currentLine] = Integer.parseInt(data[0]);
			        
			        //converts the arrival time string into an int with the format "hhmmss"
			        timeStr = data[1].split(":");
			        //ensures theres no invalid times such as 24
			        //replaces " " with 0
			        timeStr[0] = timeStr[0].replace(" ", "0");
			        hours = (Integer.parseInt(timeStr[0])) % 24;
			        timeStr[1] = timeStr[1].replace(" ", "0");
			        minutes = Integer.parseInt(timeStr[1]);
			        timeStr[2] = timeStr[2].replace(" ", "0");
			        seconds = Integer.parseInt(timeStr[2]);
					arrivalTimes[currentLine] = (hours*10000) + (minutes*100) + seconds;
					
					//converts the departure time string into an int with the format "hhmmss"
					timeStr = data[2].split(":");
			        //ensures theres no invalid times such as 27
					 //replaces " " with 0
					timeStr[0] = timeStr[0].replace(" ", "0");
			        hours = Integer.parseInt(timeStr[0]) % 24;
			        timeStr[1] = timeStr[1].replace(" ", "0");
			        minutes = Integer.parseInt(timeStr[1]);
			        timeStr[2] = timeStr[2].replace(" ", "0");
			        seconds = Integer.parseInt(timeStr[2]);
			        departureTimes[currentLine] = (hours*10000) + (minutes*100) + seconds;
					
			        stopID[currentLine] = Integer.parseInt(data[3]);
			        stopSequence[currentLine] = Integer.parseInt(data[4]);
					pickupType[currentLine] = Integer.parseInt(data[6]);
					dropoffType[currentLine] = Integer.parseInt(data[7]);
					if(data.length == 9) {
						distTravelled[currentLine] = Double.parseDouble(data[8]);
					}
		    	}
				currentLine++;
		    }
		}
		catch (IOException e) {
		    System.out.println("File Read Error");
		}
	}
	
	//finds the trips with a given arrival time
	public tripsInfo findTrips(int time) {
		
		ArrayList<Integer> matchingTimesIndex = findMatchingTimesindexes(arrivalTimes, time);
		
		if(matchingTimesIndex == null) {
			return null;
		}
		
		ArrayList<Integer> tripIDTmp = new ArrayList<Integer>();
		ArrayList<Integer> arrivalTimesTmp  = new ArrayList<Integer>();
		ArrayList<Integer> departureTimesTmp  = new ArrayList<Integer>();
		ArrayList<Integer> stopIDTmp = new ArrayList<Integer>();
		ArrayList<Integer> stopSequenceTmp = new ArrayList<Integer>();
		ArrayList<Integer> pickupTypeTmp = new ArrayList<Integer>();
		ArrayList<Integer> dropoffTypeTmp = new ArrayList<Integer>();
		ArrayList<Double> distTravelledTmp = new ArrayList<Double>();
		
		//adds all the the stops in a trip and add them to the temp arraylists in ascending order
		for(int i = 0; i < matchingTimesIndex.size(); i++) {
			int currenttripID = tripID[matchingTimesIndex.get(i)];
			int endOfTripIndex = matchingTimesIndex.get(i);
			
			//gets the end of the trips
			while(tripID[endOfTripIndex] == currenttripID ) {
				endOfTripIndex++;
			}
			
			//fixes the index
			endOfTripIndex = endOfTripIndex - 1;
				
			// adds the entirety of the trip to the arrayList ordered
			for(int j = stopSequence[endOfTripIndex] - 1; j >= 0; j--) {
				tripIDTmp.add(tripID[endOfTripIndex - j]);
				arrivalTimesTmp.add(arrivalTimes[endOfTripIndex - j]);
				departureTimesTmp.add(departureTimes[endOfTripIndex - j]);
				stopIDTmp.add(stopID[endOfTripIndex - j]);
				stopSequenceTmp.add(stopSequence[endOfTripIndex - j]);
				pickupTypeTmp.add(pickupType[endOfTripIndex - j]);
				dropoffTypeTmp.add(dropoffType[endOfTripIndex - j]);
				distTravelledTmp.add(distTravelled[endOfTripIndex - j]);
			}
		}
		
		tripsInfo returnable = new tripsInfo(tripIDTmp, arrivalTimesTmp, departureTimesTmp, stopIDTmp, stopSequenceTmp,
				pickupTypeTmp, dropoffTypeTmp, distTravelledTmp);
		
		return returnable;
		
	}
	
	// Finds all the indexes of the matching times and returns them ordered by their tripID
	private ArrayList<Integer> findMatchingTimesindexes(int times[], int time){
		
		ArrayList<Integer> matchingTimesIndex = new ArrayList<Integer>();
		ArrayList<Integer> tempTripID = new ArrayList<Integer>();
		boolean exists = false;
		for(int i = 0; i < times.length; i++) {
			 if(times[i] == time) {
				 matchingTimesIndex.add(i);
				 tempTripID.add(tripID[i]);
				 exists = true;
			 }
		}
		
		//checks that an arrival time exists
				if(!exists) {
					return null;
				}
				else {
					matchingTimesIndex = sortByTripID(tempTripID , matchingTimesIndex);
				}
				
				return matchingTimesIndex;
		
	}
	 // sorts the matchingTimesIndex by its corresponding trip ID
	private ArrayList<Integer> sortByTripID(ArrayList<Integer> tempTripID, ArrayList<Integer> matchingTimesIndex){
		
		int tmp;
    	int aLength = tempTripID.size();
        for(int i = 1; i < aLength; i++) {
        	for(int j = i; j > 0; j--) {
        		if(tempTripID.get(j) < tempTripID.get(j-1)) {
        			tmp = tempTripID.get(j);
        			tempTripID.set(j, tempTripID.get(j-1));
        			tempTripID.set(j-1, tmp);
        			
        			//also sorts matchingTimesIndex by the tripId
        			tmp = matchingTimesIndex.get(j);
        			matchingTimesIndex.set(j, matchingTimesIndex.get(j-1));
        			matchingTimesIndex.set(j-1, tmp);
        		}
        	}
        }
        
        return matchingTimesIndex;
		
	}
	
}


